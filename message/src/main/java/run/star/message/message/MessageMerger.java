/**
 * qccr.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package run.star.message.message;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 钉钉消息合并器
 *
 * @author hecs
 * @version $: MessageMerger.java v1.0  2019年12月23日 14:06:58 hecs Exp $
 * @name MessageMerger
 */
public class MessageMerger {

    /**
     * markdown消息换行符
     */
    private static final String CONTENT_NEWLINE = "######################\n \n";

    public static List<Message> groupMerge(List<Message> messages) {
        Map<String, List<Message>> messageGroup = messages.stream().collect(Collectors.groupingBy(Message::getReceiveUrl
                , Collectors.toList()));
        return messageGroup.values().stream().map(MessageMerger::merge).collect(Collectors.toList());
    }


    public static Message merge(List<Message> messages) {
        Message msg = new Message();
        msg.setTitle(messages.get(0).getTitle());
        msg.setReceiveUrl(messages.get(0).getReceiveUrl());
        msg.setContent(messages.stream().map(Message::getContent).collect(Collectors.joining(CONTENT_NEWLINE)));
        return msg;
    }

    public static int mergeSize(List<Message> messages) {
        return messages.stream().map(m -> m.getContent().length()).reduce(Integer::sum).get();
    }

}
