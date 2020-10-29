/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.star.message.core.MessageCore;
import run.star.message.core.MessageCoreFactory;
import run.star.message.exception.MessageException;
import run.star.message.message.Message;

/**
 * 消息发送器 客户端
 *
 * @author hecs
 * @version $: MessageSendClient.java v1.0  2019年05月07日 15:46:37 hecs Exp $
 * @name MessageSendClient
 */
public class MessageSendClient {

    private static Logger logger = LoggerFactory.getLogger(MessageSendClient.class);

    private MessageSendClient() {
    }

    public static boolean send(Message message) {

        MessageCore messageCore = MessageCoreFactory.getInstance().create();

        try {
            messageCore.send(message);
        } catch (MessageException e) {
            logger.error("MessageSendClient send failed", e);
            return false;
        }

        return true;
    }

}
