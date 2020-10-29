package run.star.message.message;

import com.alibaba.fastjson.JSON;
import run.star.message.config.MessageCfgNames;
import run.star.message.entry.MessageProps;
import run.star.message.sender.DingDingMessageSender;

import java.util.*;

/**
 * 钉钉消息建造器 use MarkdownMessageBuilder
 *
 * @author hecs
 * @version $$Id: DingDingMessageBuilder.java, v 0.1 2018/9/19 17:37 hecs Exp $$
 */
@Deprecated
public class DingDingMessageBuilder {

    /**
     * 钉钉markdown消息引用符
     */
    private static final String CONTENT_QUOTE = "> ";

    /**
     * 钉钉markdown消息换行符
     */
    private static final String CONTENT_NEWLINE = "\n \n";

    /**
     * 钉钉markdown消息加粗符
     */
    private static final String CONTENT_BOLD = "**%s**";

    /**
     * 标题格式
     */
    private static final String TITLE_PREFIX = "## ";

    private Set<String> receivers = new HashSet<>();

    private String title;

    private String receiveUrl;

    private StringBuilder contentBuilder = new StringBuilder();

    public static String bold(String text) {
        return String.format(CONTENT_BOLD, text);
    }

    public static DingDingMessageBuilder newBuilder() {
        return new DingDingMessageBuilder();
    }

    public DingDingMessageBuilder addReceivers(List<String> receivers) {
        this.receivers.addAll(receivers);
        return this;
    }

    public DingDingMessageBuilder addReceiver(String receiver) {
        receivers.add(receiver);
        return this;
    }

    public DingDingMessageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DingDingMessageBuilder setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
        return this;
    }

    public DingDingMessageBuilder appendContent(Map<String, Object> infoMap) {
        if (infoMap.isEmpty()) {
            return this;
        }
        infoMap.forEach((k, v) -> this.appendContentln(bold(k) + ": " + JSON.toJSONString(v)));
        return this;
    }

    public DingDingMessageBuilder appendContent(String content) {
        contentBuilder.append(CONTENT_QUOTE).append(content);
        return this;
    }

    public DingDingMessageBuilder appendContentln(String line) {
        contentBuilder.append(CONTENT_QUOTE).append(line).append(CONTENT_NEWLINE);
        return this;
    }

    public Message build() {
        Message message = new Message();
        message.setReceivers(new LinkedList<>(receivers));
        message.setTitle("通知-" + title);
        message.setContent(CONTENT_QUOTE + TITLE_PREFIX + title + CONTENT_NEWLINE + contentBuilder.toString());
        if (receiveUrl != null) {
            message.setReceiveUrl(receiveUrl);
        } else {
            message.setReceiveUrl(MessageProps.get().get(MessageCfgNames.DINGDING_DEFAULT_RECEIVE_URL));
        }
        message.setSenderName(DingDingMessageSender.NAME);
        return message;
    }
}
