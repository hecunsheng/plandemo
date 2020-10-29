/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.core;

import run.star.message.message.Message;

import java.util.LinkedList;
import java.util.List;

/**
 * 消息 上下文
 *
 * @author hecs
 * @version $: MessageContext.java v1.0  2019年05月07日 14:53:43 hecs Exp $
 * @name MessageContext
 */
public class MessageContext {

    private List<Message> messages;

    private List<Message> failedMessages;

    public MessageContext(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getFailedMessages() {
        return failedMessages;
    }

    public synchronized void addFailedMessages(List<Message> failedMessages) {
        if (this.failedMessages == null) {
            this.failedMessages = new LinkedList<>();
        }
        this.failedMessages.addAll(failedMessages);
    }

    public synchronized void addFailedMessage(Message failedMessage) {
        if (this.failedMessages == null) {
            this.failedMessages = new LinkedList<>();
        }
        this.failedMessages.add(failedMessage);
    }
}
