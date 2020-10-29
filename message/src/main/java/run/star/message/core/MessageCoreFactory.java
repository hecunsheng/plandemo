package run.star.message.core;

import run.star.message.config.DefaultCfg;
import run.star.message.config.MessageCfgNames;
import run.star.message.entry.MessageProps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂类
 * <p>
 * 目的管理core
 *
 * @author hecs
 * @version $$Id: MessageCoreFactory.java, v 0.1 2018/9/19 16:34 hecs Exp $$
 */
public class MessageCoreFactory {

    private static volatile MessageCoreFactory instance;

    private static MessageCoreConfig DEFAULT_CONFIG = new MessageCoreConfig();

    private Map<String, MessageCore> messageCores = new ConcurrentHashMap<>();

    static {
        String coreName = MessageProps.get().getOrDefault(MessageCfgNames.MSG_CORE_NAME
                , "默认消息发送管家");
        DEFAULT_CONFIG.setName(coreName);
        DEFAULT_CONFIG.setSendInterval(DefaultCfg.sendInterval);
        DEFAULT_CONFIG.setRestInterval(DefaultCfg.restInterval);
        DEFAULT_CONFIG.setQueueMaxLength(DefaultCfg.queueMaxLength);
    }

    private MessageCoreFactory() {

    }

    public static MessageCoreFactory getInstance() {

        if (instance == null) {
            synchronized (MessageCoreFactory.class) {
                if (instance == null) {
                    instance = new MessageCoreFactory();
                }
            }
        }

        return instance;
    }

    public MessageCore create() {
        return create(DEFAULT_CONFIG);
    }


    private MessageCore create(MessageCoreConfig config) {
        MessageCore core = messageCores.get(config.getName());
        if (core == null) {
            synchronized (this) {
                core = messageCores.get(config.getName());
                if (core == null) {
                    core = new MessageCore(config);
                    messageCores.put(core.getName(), core);
                }
            }
        }
        return core;
    }

}
