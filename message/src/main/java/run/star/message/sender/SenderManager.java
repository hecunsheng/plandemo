/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.sender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sender 管理器
 *
 * @author hecs
 * @version $: SenderManager.java v1.0  2020年06月17日 16:34:15 hecs Exp $
 * @name SenderManager
 */
public class SenderManager {

    private static SenderManager senderManager = new SenderManager();

    private Map<String, MessageSender> register = new HashMap<>(10);

    private SenderManager() {
        register(new DingDingMessageSender());
        register(new WechatMessageSender());
    }

    public static SenderManager getInstance() {
        return senderManager;
    }

    public void register(MessageSender sender) {
        this.register.put(sender.getName(), sender);
    }

    public List<MessageSender> getSenders() {
        return new ArrayList<>(register.values());
    }

    public MessageSender getSender(String senderName) {
        return register.get(senderName);
    }
}
