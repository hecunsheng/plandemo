package run.star.plan.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.concurrent.TimeUnit;

/**
 * @author hecs
 * @date 2020-10-12 13:32
 */
public class GuavaTest {

    public static void main(String[] args) throws Exception {
        //基于缓存权重
        LoadingCache<String, Integer> loadingCache = CacheBuilder.newBuilder()
                .maximumWeight(9)
                .weigher(new Weigher<String, Integer>() {
                    public int weigh(String k, Integer v) {
                        return v;    //v的权重设为其本身；
                    }
                })
                .recordStats()
                .build(
                        new CacheLoader<String, Integer>() {
                            int num = 1;

                            public Integer load(String key) {
                                return num++;  //num初始值为1；
                            }
                        });
        System.out.println(loadingCache.get("a"));
        System.out.println(loadingCache.get("b"));
        System.out.println(loadingCache.get("c"));
        System.out.println(loadingCache.get("d"));
        //输出结果为1  2  3  4  1，
        //当设置为maximumWeight(9)时，输出结果即为1  2  3  4  5，
        //因为在get("d")时，权重值和已超过最大值9，a被移出，get("a")时，需重新加载，此时num为5
        System.out.println(loadingCache.get("a"));


        //基于时间 监控加载/命中情况
        //expireAfterAccess(long, TimeUnit) 最后一次访问后的一段时间移出
        //expireAfterWrite(long, TimeUnit) 最后一次写入后的一段时间移出
//        expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
//        expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。
//        refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                //最多存放十个数据
                .maximumSize(10)
                //缓存10秒，10秒之后进行回收
                .expireAfterWrite(10, TimeUnit.SECONDS)
                //移除时触发
                .removalListener(removalNotification -> {
                    System.out.println("移除：" + removalNotification.getKey());
                })
                .recordStats()//开启，记录状态数据功能
                //当key的值不存在时触发
                .build(new CacheLoader<String, Integer>() {
                    //数据加载，也可以是查询操作，如从redis/db
                    @Override
                    public Integer load(String key) throws Exception {
                        System.out.println("没有缓存，开始加载新缓存");
                        //这里可以对数据库 或者redis进行查询并加入缓存。
                        return -1;
                    }
                });
        //查询缓存，未命中，调用load方法，这里可以单独对一个key进行load操作。
        //如果cache对象是公用缓存，可以在不同业务时对load进行重写获取最新缓存
        System.out.println(cache.get("key2", () -> -1));
        //put数据，更新缓存
        cache.put("key2", 2);
        //查询得到最新的数据
        System.out.println(cache.get("key2"));
        System.out.println("size:" + cache.size());
        //插入十个数据
        for (int i = 3; i < 13; i++) {
            cache.put("key" + i, i);
        }
        //超过最大容量，删除最早插入的数据
        System.out.println("size:" + cache.size());
        System.out.println(cache.getIfPresent("key2"));
        //等待5秒
        Thread.sleep(5000);
        //此时key已经失效，但是size没有更新
        System.out.println("size :" + cache.size());
        //获取key2，发现已经失效，然后刷新缓存，遍历数据，去掉失效的所有数据
        System.out.println(cache.getIfPresent("key2"));
        System.out.println("size :" + cache.size());
    }
}
