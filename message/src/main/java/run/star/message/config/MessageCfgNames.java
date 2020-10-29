package run.star.message.config;

/**
 * MessageCfgNames
 *
 * @author hecs
 * @version $$Id: MessageCfgNames.java, v 0.1 2018/4/18 14:41 hecs Exp $$
 */
public interface MessageCfgNames {

    /**
     * 钉钉默认消息接收机器人地址
     */
    String DINGDING_DEFAULT_RECEIVE_URL = "msg.dd.default.receive.url";

    /**
     * 微信默认消息接收机器人地址
     */
    String WECHAT_DEFAULT_RECEIVE_URL = "msg.wx.default.receive.url";

    /**
     * 核心名称
     */
    String MSG_CORE_NAME = "msg.core.name";

    /**
     * 默认发送器名称
     */
    String DEFAULT_SENDER_NAME = "msg.default.sender.name";

    /**
     * 发送间隔，控制发送频率
     */
    String SEND_INTERVAL = "msg.send.interval";

    /**
     * 健康监测间隔, 超过此间隔没有检测到信号，认为sendThread dead
     */
    @Deprecated
    String HEALTH_INTERVAL = "msg.health.interval";

    /**
     * 发送线程休息间隔 = 健康监测间隔
     */
    String REST_INTERVAL = "msg.rest.interval";

    /**
     * 消息缓存队列最大长度， 默认 10000
     */
    String QUEUE_MAX_LENGTH = "msg.queue.max.length";

    /**
     * 只开启线上发送，避免线上线下竞争
     */
    String PRD_SEND_SWITCH = "msg.only.prd";

}
