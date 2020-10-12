package run.star;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            RedissonClient finalRedisson = Redisson.create(config);
            //需求数量
            int requireQty = 9;
            for (int i = 0; i < 30; i++) {
                Thread t = new Thread(() -> {
                    try {
                        RLock rLock = finalRedisson.getLock("myLock");
                        System.out.println(Thread.currentThread().getName() + " 开始");
                        //库存商品 100
                        RAtomicLong stockQty = finalRedisson.getAtomicLong("stockQty");
                        //模拟库存占用
                        RAtomicLong stockOccupy = finalRedisson.getAtomicLong("stockOccupy");
                        rLock.lock();
                        long l1 = stockQty.get();
                        long l2 = stockOccupy.get();
                        long l = l1 - l2;
                        if (l >= requireQty) {
                            stockOccupy.set(l2 + requireQty);
                            System.out.println(Thread.currentThread().getName() + " done，库存剩下：" + l);
                        }else {
                            System.out.println(Thread.currentThread().getName() + " 库存不足，库存剩下：" + l);
                            //TODO 扣减库存 库存数量目前还是100 什么时候同步取决业务(放在晚上处理也可以)
                        }
                        rLock.unlock();

                        System.out.println(Thread.currentThread().getName() + " 结束");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                });
                t.start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {

        }
    }
}
