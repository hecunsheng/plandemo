/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.message;

import com.alibaba.fastjson.JSON;
import run.star.message.config.DingDingCfg;
import run.star.message.config.MessageCfgNames;
import run.star.message.config.WechatCfg;
import run.star.message.entry.MessageProps;
import run.star.message.exception.MessageException;
import run.star.message.sender.DingDingMessageSender;
import run.star.message.sender.WechatMessageSender;

import java.util.*;

/**
 * 微信消息构造器
 *
 * @author hecs
 * @version $: MarkdownMessageBuilder.java v1.0  2020年06月17日 16:11:22 hecs Exp $
 * @name MarkdownMessageBuilder
 */
public class MarkdownMessageBuilder {

    /**
     * markdown消息引用符
     */
    private static final String CONTENT_QUOTE = "> ";

    /**
     * markdown消息换行符
     */
    private static final String CONTENT_NEWLINE = "     \r";

    /**
     * markdown消息加粗符
     */
    private static final String CONTENT_BOLD = "**%s**";

    /**
     * 标题格式
     */
    private static final String TITLE_PREFIX = "## ";

    /**
     * 消息发送器，默认根据 receiveUrl 判断是微信还是钉钉
     */
    private String senderName;

    private Set<String> receivers = new HashSet<>();

    private String title;

    private String receiveUrl;

    private StringBuilder contentBuilder = new StringBuilder();

    public static String bold(String text) {
        return String.format(CONTENT_BOLD, text);
    }

    public static MarkdownMessageBuilder newBuilder() {
        return new MarkdownMessageBuilder();
    }

    public MarkdownMessageBuilder addReceivers(List<String> receivers) {
        this.receivers.addAll(receivers);
        return this;
    }

    public MarkdownMessageBuilder addReceiver(String receiver) {
        receivers.add(receiver);
        return this;
    }

    public MarkdownMessageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MarkdownMessageBuilder setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
        if (receiveUrl.contains(DingDingCfg.HOST)) {
            this.senderName = DingDingMessageSender.NAME;
        } else if (receiveUrl.contains(WechatCfg.HOST)) {
            this.senderName = WechatMessageSender.NAME;
        }
        return this;
    }

    public MarkdownMessageBuilder appendContent(Map<String, Object> infoMap) {
        if (infoMap.isEmpty()) {
            return this;
        }
        infoMap.forEach((k, v) -> this.appendContentln(bold(k) + ": " + JSON.toJSONString(v)));
        return this;
    }

    public MarkdownMessageBuilder appendContent(String content) {
        contentBuilder.append(CONTENT_QUOTE).append(content);
        return this;
    }

    public MarkdownMessageBuilder appendContentln(String line) {
        contentBuilder.append(CONTENT_QUOTE).append(line).append(CONTENT_NEWLINE);
        return this;
    }

    public String getSenderName() {
        return senderName;
    }

    public MarkdownMessageBuilder setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public Message build() {
        Message message = new Message();
        message.setReceivers(new LinkedList<>(receivers));
        //钉钉默认过滤词
        message.setTitle("通知-" + title);
        message.setContent(CONTENT_QUOTE + TITLE_PREFIX + title + CONTENT_NEWLINE + contentBuilder.toString());

        if (receiveUrl != null) {
            message.setReceiveUrl(receiveUrl);
        } else {
            if (DingDingMessageSender.NAME.equals(this.senderName)) {
                message.setReceiveUrl(MessageProps.get().get(MessageCfgNames.DINGDING_DEFAULT_RECEIVE_URL));
            } else {
                //不传URL和SENDER，默认使用微信SENDER
                message.setReceiveUrl(MessageProps.get().get(MessageCfgNames.WECHAT_DEFAULT_RECEIVE_URL));
            }
        }

        if (senderName != null) {
            message.setSenderName(senderName);
        } else if (receiveUrl != null) {
            if (receiveUrl.contains(DingDingCfg.HOST)) {
                this.senderName = DingDingMessageSender.NAME;
            } else if (receiveUrl.contains(WechatCfg.HOST)) {
                this.senderName = WechatMessageSender.NAME;
            }
        }

        if (receiveUrl == null) {
            throw new MessageException("请配置默认消息接收地址，或者传入消息接收地址");
        }

        return message;
    }

}
