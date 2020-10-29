/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.config;

/**
 * 钉钉消息配置
 *
 * @author hecs
 * @version $: DingDingCfg.java v1.0  2020年01月02日 09:58:45 hecs Exp $
 * @name DingDingCfg
 */
public interface DingDingCfg extends DefaultCfg {

    /**
     * 消息标题最大长度
     */
    int TITLE_MAX_LENGTH = 64;

    /**
     * 内容最大长度
     */
    int CONTENT_MAX_LENGTH = 4096;

    String HOST = "oapi.dingtalk.com";
}
