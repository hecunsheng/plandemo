package run.star.message.core;

import lombok.Getter;
import lombok.Setter;

/**
 * 核心配置
 *
 * @author hecs
 * @version $$Id: MessageCoreConfig.java, v 0.1 2018/7/10 14:25 hecs Exp $$
 */

@Getter
@Setter
public class MessageCoreConfig {

    /**
     * 核心名称
     */
    private String name;

    /**
     * 默认发送器名称
     */
    private String defaultSenderName;

    /**
     * 发送间隔，控制发送频率
     */
    private long sendInterval;

    /**
     * 发送线程休息间隔
     */
    private long restInterval;

    /**
     * 消息缓存队列最大长度， 默认 10000
     */
    private long queueMaxLength;
}
