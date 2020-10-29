package run.star.message.mq;

import com.google.common.collect.Lists;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.star.message.config.DefaultCfg;
import run.star.message.message.Message;

import java.util.List;

/**
 * 监控消息队列
 *
 * @author hecs
 * @version $$Id: MessageQueue.java, v 0.1 2018/4/19 14:10 hecs Exp $$
 */
public class MessageQueue {

    private static Logger logger = LoggerFactory.getLogger(MessageQueue.class);

//    private static final OnecachePlugin ONECACHE = Insight.plugins().get(OnecachePlugin.class);

    private static final RedissonClient finalRedisson;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        finalRedisson = Redisson.create(config);
    }

    private static final String MESSAGE_QUEUE_KEY = "DEFAULT_MESSAGE_LIST";

    private MessageQueue() {
    }

    public static boolean checkIsOverLimit(String queueName) {
        RList<Message> rList = finalRedisson.getList(queueName);
        return rList.size() >= DefaultCfg.queueMaxLength;
    }

    public static void push(Message message, String queueName) {
        RList<Message> rList = finalRedisson.getList(queueName);
        rList.add(message);
    }

    //    public static Message pop(String queueName) {
//        RList<Message> rList = finalRedisson.getList(queueName);
//        return rList.rpop();
//    }
//
//    public static void pushToHead(Message message, String queueName) {
//        RList<Message> rList = finalRedisson.getList(queueName);
//        rList.rpush(message);
//    }
//
//    public static void push(Message message) {
//        RList<Message> rList = finalRedisson.getList(MESSAGE_QUEUE_KEY);
//        rList.lpush(message);
//    }
//
    public static Message pop() {
        RList<Message> rList = finalRedisson.getList(MESSAGE_QUEUE_KEY);
        return rList.get(1);
    }

    public static List<Message> pop(String queueName, int size) {
        RList<Message> rList = finalRedisson.getList(queueName);
        int i = 0;
        List<Message> messages = Lists.newLinkedList();
        while (i++ < size) {
            Message m = rList.get(i);
            if (m != null) {
                messages.add(m);
            } else {
                break;
            }
        }
        return messages;
    }
//
//
//    public static List<Message> pop(int size) {
//        return pop(MESSAGE_QUEUE_KEY, size);
//    }
//
//    public static void pushToHead(Message message) {
//        RList<Message> rList = finalRedisson.getList(MESSAGE_QUEUE_KEY);
//        rList.rpush(message);
//    }
}
