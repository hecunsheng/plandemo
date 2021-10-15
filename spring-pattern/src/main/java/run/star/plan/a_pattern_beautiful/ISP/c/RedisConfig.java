package run.star.plan.a_pattern_beautiful.ISP.c;

import java.util.Map;

/**
 * @author hecs
 * @date 2021/7/19 21:04
 */

public class RedisConfig implements Updater, Viewer{
    private ConfigSource configSource; //配置中心（比如zookeeper）
    private String address;
    private int timeout;
    private int maxTotal;
    //省略其他配置: maxWaitMillis,maxIdle,minIdle...

    public RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public String getAddress() {
        return this.address;
    }
    //...省略其他get()、init()方法...
    @Override
    public void update() {
        System.out.println("RedisConfig update");
        //从configSource加载配置到address/timeout/maxTotal...
    }

    @Override
    public String outputInPlainText() {
        return "RedisConfig.outputInPlainText";
    }

    @Override
    public Map output() {
        return null;
    }
}
