/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.entry;

import run.star.message.config.MessageCfgNames;

/**
 * 消息开关
 *
 * @author hecs
 * @version $: MessageSwitch.java v1.0  2020年01月10日 09:06:37 hecs Exp $
 * @name MessageSwitch
 */
public class MessageSwitch {

    private static boolean isOnlyPrdEnable = false;

    public static boolean isOnlyPrdEnable() {
        Boolean switchStatus = MessageProps.get().getBoolean(MessageCfgNames.PRD_SEND_SWITCH);
        if (switchStatus == null) {
            return isOnlyPrdEnable;
        }
        return switchStatus;
    }

    public static void setOnlyPrdEnable() {
        isOnlyPrdEnable = true;
    }

}
