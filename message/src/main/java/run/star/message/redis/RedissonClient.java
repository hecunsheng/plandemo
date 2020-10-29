package run.star.message.redis;

import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * @author hecs
 * @date 2020-10-29 20:39
 */
public class RedissonClient {
    private static org.redisson.api.RedissonClient REDISSON_CLIENT;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        REDISSON_CLIENT = Redisson.create(config);
    }
}
