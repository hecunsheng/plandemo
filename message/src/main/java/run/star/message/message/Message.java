package run.star.message.message;

import lombok.ToString;
import run.star.message.sender.MessageSender;

import java.io.Serializable;
import java.util.List;

/**
 * 消息
 *
 * @author hecs
 * @version $$Id: Message.java, v 0.1 2018/4/18 11:37 hecs Exp $$
 */
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = -4232432627087218501L;

    /**
     * 发送器名称 {@link MessageSender} getName
     */
    private String senderName;

    private List<String> receivers;

    private String title;

    private String content;

    private String receiveUrl;

    Message() {

    }

    public List<String> getReceivers() {
        return receivers;
    }

    public Message setReceivers(List<String> receivers) {
        this.receivers = receivers;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Message setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public String getReceiveUrl() {
        return receiveUrl;
    }

    public Message setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
        return this;
    }

    public String getSenderName() {
        return senderName;
    }

    public Message setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }
}
