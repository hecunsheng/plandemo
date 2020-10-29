package run.star.message.sender;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import run.star.message.core.MessageContext;
import run.star.message.message.Message;

import java.util.concurrent.Executor;

/**
 * 信息发送工具接口
 *
 * @author hecs
 * @version $$Id: MessageSender.java, v 0.1 2018/4/18 11:36 hecs Exp $$
 */
public interface MessageSender extends Runnable {

    String HEALTH_WATCHER_KEY = "MESSAGE_SENDER_WATCHER:";

    String SENDER_LOCK_KEY = "MESSAGE_SENDER_LOCK";

    /**
     * 检查消息格式
     *
     * @param message 消息体
     * @return 是否合法消息
     */
    boolean checkMessage(Message message);

    /**
     * 发送消息
     *
     * @param messageContext 消息上下文
     * @return 成功或者失败
     */
    boolean send(MessageContext messageContext);

    /**
     * 获取 sender名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 是否已有sender 存活
     *
     * @return alive or die
     */
    default boolean isAlive() {
//        OnecachePlugin onecachePlugin = Insight.plugins().get(OnecachePlugin.class);
//        RLong watcher = onecachePlugin.getRLong(HEALTH_WATCHER_KEY + this.getName());
//        Long lastLiveMills = watcher.get();
//        if (lastLiveMills != null) {
//            long duration = System.currentTimeMillis() - lastLiveMills;
//            //监视器正常间隔监视，表示已有sender存活, 不需要实例化sender 线程
//            if (duration <= DefaultCfg.restInterval) {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * 启动sender
     *
     * @param executor 接收外部执行器
     */
    default void start(Executor executor) {
        if (this.isAlive()) {
            return;
        }
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient finalRedisson = Redisson.create(config);
        //分布式锁保障一个项目多个服务器实例同时只启动一个sender
        RLock rLock = finalRedisson.getLock(SENDER_LOCK_KEY);
        rLock.lock();
        executor.execute(this);
        rLock.unlock();
    }

    /**
     * sender 启动后记录心跳
     */
    default void heartBeat() {
//        OnecachePlugin onecachePlugin = Insight.plugins().get(OnecachePlugin.class);
//        RLong watcher = onecachePlugin.getRLong(HEALTH_WATCHER_KEY + this.getName());
//        watcher.set(System.currentTimeMillis());
    }

    /**
     * 获取 消息队列 名称
     *
     * @return 名称
     */
    default String getMqName() {
        return getName() + "_MESSAGE_LIST";
    }

}
