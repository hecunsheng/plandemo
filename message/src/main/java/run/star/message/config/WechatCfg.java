/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.config;

/**
 * 接口详细说明
 *
 * @author hecs
 * @version $: WechatCfg.java v1.0  2020年06月17日 16:17:16 hecs Exp $
 * @name WechatCfg
 */
public interface WechatCfg extends DefaultCfg {

    /**
     * 消息标题最大长度
     */
    int TITLE_MAX_LENGTH = 64;

    /**
     * 内容最大长度, 微信限制 4096个字节， java 字符串 字符char 是两个字节，所以除以二作为最大长度
     */
    int CONTENT_MAX_LENGTH = 4096 / 2;

    String HOST = "qyapi.weixin.qq.com";

}
