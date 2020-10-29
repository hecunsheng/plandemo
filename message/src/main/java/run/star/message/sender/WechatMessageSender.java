package run.star.message.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.star.message.config.DefaultCfg;
import run.star.message.config.WechatCfg;
import run.star.message.core.MessageContext;
import run.star.message.message.MarkdownMessageBuilder;
import run.star.message.message.Message;
import run.star.message.message.MessageMerger;
import run.star.message.mq.MessageQueue;
import run.star.message.util.HttpClientUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 微信群消息发送
 *
 * @author hecs
 * @version $$Id: DingDingMessageSender.java, v 0.1 2018/4/18 11:39 hecs Exp $$
 */
public class WechatMessageSender implements MessageSender {

    private static Logger logger = LoggerFactory.getLogger(WechatMessageSender.class);

    public static final String NAME = "Wechat";

    /**
     * 消息内存管理队列
     */
    private Map<String, List<Message>> messageQueues = new HashMap<>();

    /**
     * 消息时钟，时钟到了，才能发送
     */
    private Map<String, Long> messageClock = new HashMap<>();

    @Override
    public boolean checkMessage(Message message) {
        if (message == null) {
            return false;
        }

        if (StringUtils.isBlank(message.getContent())) {
            logger.error("消息内容为空" + JSON.toJSONString(message));
            return false;
        }

        if (message.getContent().length() > WechatCfg.CONTENT_MAX_LENGTH) {
            logger.error("消息内容超过最大限制 " + WechatCfg.CONTENT_MAX_LENGTH
                    + ", " + JSON.toJSONString(message));
            return false;
        }
        if (message.getTitle().length() > WechatCfg.TITLE_MAX_LENGTH) {
            logger.error("消息标题超过最大限制 " + WechatCfg.TITLE_MAX_LENGTH
                    + ", " + JSON.toJSONString(message));
            return false;
        }
        if (StringUtils.isBlank(message.getReceiveUrl())) {
            logger.error("消息接受地址为空, " + JSON.toJSONString(message));
            return false;
        }

        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * 一分钟 一个 URL 调用控制在 n 次 的功能
     * <p>
     * 主要是累积合并消息 和 定时发送的功能
     */
    @Override
    public boolean send(MessageContext messageContext) {
        List<Message> messages = messageContext.getMessages();
        Map<String, List<Message>> messageGroupMap = messages.stream().filter(m -> m.getReceiveUrl() != null)
                .collect(Collectors.groupingBy(Message::getReceiveUrl, Collectors.toList()));
        messageGroupMap.values().forEach(
                msgs -> {
                    List<List<Message>> messageGroups = splitMessages(msgs);
                    for (List<Message> messageGroup : messageGroups) {
                        Message message = MessageMerger.merge(messageGroup);
                        boolean success = this.sendMessage(message);
                        if (!success) {
                            messageContext.addFailedMessage(message);
                        }
                    }
                }
        );
        return CollectionUtils.isEmpty(messageContext.getFailedMessages());
    }

    @Override
    public void run() {

        while (true) {

            //心跳
            heartBeat();

            int initialUnSendMsgTotalNum = messageQueues.values().stream().mapToInt(List::size).sum();
            //遍历队列找到可以发送的 URL，进行发送(针对URL进行限流)
            for (List<Message> subMsgQueue : messageQueues.values()) {
                if (subMsgQueue.isEmpty()) {
                    continue;
                }
                if (!checkSendInternal(subMsgQueue.get(0))) {
                    continue;
                }
                List<Message> sendMsgs = new LinkedList<>(subMsgQueue);
                subMsgQueue.clear();
                MessageContext messageContext = new MessageContext(sendMsgs);
                boolean success = this.send(messageContext);
                if (!success) {
                    subMsgQueue.addAll(messageContext.getFailedMessages());
                }
            }

            int leftUnSendMsgTotalSum = messageQueues.values().stream().mapToInt(List::size).sum();
            //如果失败总数大于发送总数1/3，说明发送任务基本都失败，等待1s再启动重发，这个时候不从redis 拉取到内存
            if (leftUnSendMsgTotalSum > initialUnSendMsgTotalNum / 3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("MessageCore 微信消息发送器终止", e);
                    break;
                }
            } else {
                //从redis 拉取数据到内存，整理批量发送
                List<Message> messages = MessageQueue.pop(this.getMqName(), 20);
                if (!messages.isEmpty()) {
                    messages.forEach(m -> {
                        List<Message> msgGroup = messageQueues.computeIfAbsent(m.getReceiveUrl(), k -> new LinkedList<>());
                        msgGroup.add(m);
                    });
                } else {
                    //说明比较空闲，等待休息间隔重试
                    if (logger.isDebugEnabled()) {
                        logger.debug(this.getName() + "：没有需要发送的消息");
                    }
                    try {
                        Thread.sleep(DefaultCfg.restInterval);
                    } catch (InterruptedException e) {
                        logger.error("MessageCore 微信消息发送器终止", e);
                        break;
                    }
                }
            }
        }

    }

    /**
     * 合并长度超过限制，需要拆分合并， 拆分策略， size / 2 合并 ，进行递归
     *
     * @param messages 消息列表
     * @return
     * @name splitMessages
     * @author hecs
     * @date 2020年01月02日 10:27:43
     */
    private List<List<Message>> splitMessages(List<Message> messages) {
        List<List<Message>> msgGroups = new ArrayList<>(2);
        if (messages.size() == 1 || MessageMerger.mergeSize(messages) <= WechatCfg.CONTENT_MAX_LENGTH) {
            msgGroups.add(messages);
            return msgGroups;
        }

        int mid = messages.size() / 2;

        List<Message> left = new ArrayList<>(messages.subList(0, mid));
        if (MessageMerger.mergeSize(left) > WechatCfg.CONTENT_MAX_LENGTH) {
            msgGroups.addAll(splitMessages(left));
        } else {
            msgGroups.add(left);
        }

        List<Message> right = new ArrayList<>(messages.subList(mid, messages.size()));
        if (MessageMerger.mergeSize(right) > WechatCfg.CONTENT_MAX_LENGTH) {
            msgGroups.addAll(splitMessages(right));
        } else {
            msgGroups.add(right);
        }

        return msgGroups;
    }

    public boolean sendMessage(Message message) {

        if (!checkSendInternal(message)) {
            return false;
        }

        Map<String, Object> params = new HashMap<>(4);
        params.put("msgtype", "markdown");

        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("content", message.getContent());

        params.put("markdown", contentMap);

        String res = HttpClientUtil.postJsonBySSL(message.getReceiveUrl(), params);
        JSONObject resObj = JSON.parseObject(res);
        if (resObj == null || !resObj.get("errcode").equals(0)) {
            logger.error("微信消息发送失败::" + res);
            return false;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("微信消息发送返回::" + res);
            }
        }

        recordSendInternal(message);
        return true;
    }

    private boolean checkSendInternal(Message message) {
        return messageClock.getOrDefault(message.getReceiveUrl(), 0L) <= System.currentTimeMillis();
    }

    private void recordSendInternal(Message message) {
        //消息队列时钟, 记录下次可以发送时间
        messageClock.putIfAbsent(message.getReceiveUrl(), System.currentTimeMillis() + DefaultCfg.sendInterval);
    }


    public static void main(String[] args) {
        Message msg = MarkdownMessageBuilder.newBuilder().setReceiveUrl("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1601f8be-469a-43cb-9666-eff0242b400d")
                .appendContent("hello world").setTitle("notice!!!").build();

        WechatMessageSender s = new WechatMessageSender();
        s.sendMessage(msg);
    }
}
