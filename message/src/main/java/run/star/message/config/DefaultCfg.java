/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.config;

import run.star.message.entry.MessageProps;

/**
 * 默认配置
 *
 * @author hecs
 * @version $: DefaultCfg.java v1.0  2020年06月17日 17:57:24 hecs Exp $
 * @name DefaultCfg
 */
public interface DefaultCfg {

    /**
     * 发送线程休息间隔
     */
    long restInterval = MessageProps.get().getOrDefault(MessageCfgNames.REST_INTERVAL
            , 5000L);

    /**
     * 消息缓存队列最大长度， 默认 10000
     */
    long queueMaxLength = MessageProps.get().getOrDefault(MessageCfgNames.QUEUE_MAX_LENGTH
            , 10000L);

    /**
     * 强制发送间隔
     */
    long sendInterval = MessageProps.get().getOrDefault(MessageCfgNames.SEND_INTERVAL
            , 1000L);

}
