package run.star.message.core;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import run.star.message.entry.MessageSwitch;
import run.star.message.exception.MessageException;
import run.star.message.message.Message;
import run.star.message.mq.MessageQueue;
import run.star.message.sender.DingDingMessageSender;
import run.star.message.sender.MessageSender;
import run.star.message.sender.SenderManager;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 消息核心，入口类
 * <p>
 * 作用：
 * 1. 提供消息发送入口
 * 2. 保证分布式多台服务器，只有一个服务器实例能够启动sender线程, 节省服务器资源（消费能力有限，设定 sendInternal 间隔只能发送一条消息）
 * 3. 没有启动sender线程的服务器，可以推送消息到队列
 *
 * @author hecs
 * @version $$Id: MessageCore.java, v 0.1 2018/7/10 14:20 hecs Exp $$
 */
public class MessageCore {

    private static Logger logger = LoggerFactory.getLogger(MessageCore.class);

    private String name;

    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    static {
        threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("MessageSender-pool-");
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setKeepAliveSeconds(120);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(false);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.initialize();
    }

    MessageCore(MessageCoreConfig messageCoreConfig) {
        this.checkConfig(messageCoreConfig);
        this.init(messageCoreConfig);
    }

    private void checkConfig(MessageCoreConfig messageCoreConfig) {
        if (logger.isInfoEnabled()) {
            logger.info("MessageCore initialize name =======" + messageCoreConfig.getName());
            logger.info("MessageCore initialize sendInterval =======" + messageCoreConfig.getSendInterval());
            logger.info("MessageCore initialize restInterval =======" + messageCoreConfig.getRestInterval());
        }
    }

    private void init(MessageCoreConfig messageCoreConfig) {
        this.name = messageCoreConfig.getName();
    }

    private MessageSender getSender(String name) {
        if (StringUtils.isBlank(name)) {
            name = DingDingMessageSender.NAME;
        }

        MessageSender sender = SenderManager.getInstance().getSender(name);
        if (sender == null) {
            throw new RuntimeException("no such sender " + name);
        }

        return sender;
    }

    /**
     * 发送消息
     *
     * @param message 消息实体
     */
    public void send(Message message) {

        MessageSender sender = getSender(message.getSenderName());
        //sender 未启动，先启动sender
        if (!sender.isAlive()) {
            sender.start(threadPoolTaskExecutor);
            logger.info("MessageCore initialize sender =======" + sender.getName());
        }

        if (!sender.checkMessage(message)) {
            throw new MessageException("消息格式异常:" + JSON.toJSONString(message));
        }

        if (MessageSwitch.isOnlyPrdEnable()) {
//            if (Insight.env().getModuleEnv() != ModuleEnvEnum.PRO) {
//                logger.error("消息开关设置只发送线上消息" + JSON.toJSONString(message));
//                return;
//            }
        }

        try {
            String queueName = sender.getMqName();
            if (MessageQueue.checkIsOverLimit(queueName)) {
                return;
            }
            MessageQueue.push(message, queueName);
        } catch (Exception e) {
            logger.error("缓存消息失败", e);
            throw new MessageException("缓存消息失败", e);
        }
    }

    public String getName() {
        return name;
    }
}
