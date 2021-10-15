package run.star.plan.a_pattern_beautiful.demo.statistics.d00;

import java.util.concurrent.TimeUnit;

/**
 * 应用场景：统计下面两个接口(注册和登录）的响应时间和访问次数
 * @Author hecs
 * @Date 2021/9/20 23:40
 */
public class UserController {

    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    public void register(UserVo user) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("regsiter", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);
    }

    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", respTime);
        return null;
    }
}
