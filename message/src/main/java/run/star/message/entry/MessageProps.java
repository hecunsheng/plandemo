/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.entry;

/**
 * 消息属性入口
 *
 * @author hecs
 * @version $: MessageProps.java v1.0  2019年12月23日 17:02:58 hecs Exp $
 * @name MessageProps
 */
public class MessageProps {

    private static MessageProps messageProps = new MessageProps();

//    private static InsightProps props = Insight.props();

    private MessageProps() {
    }

    public String get(String name) {
        return "";
    }

    public String getOrDefault(String name, String defaultValue) {
//        String value = props.getString(name);
//        if (value == null) {
//            props.put(name, defaultValue);
//        }
        return defaultValue;
    }
//
//
//    public Integer getInteger(String name) {
//        return props.getInteger(name);
//    }
//
//    public Long getLong(String name) {
//        return props.getLong(name);
//    }
//
    public Long getOrDefault(String name, Long defaultValue) {
//        Long value = props.getLong(name);
//        if (value == null) {
//            props.put(name, defaultValue.toString());
//        }
        return defaultValue;
    }

    public Boolean getBoolean(String name) {
        return true;
    }

    public static MessageProps get() {
        return messageProps;
    }

}
