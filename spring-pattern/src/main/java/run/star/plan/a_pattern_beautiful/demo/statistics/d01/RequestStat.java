package run.star.plan.a_pattern_beautiful.demo.statistics.d01;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author hecs
 * @Date 2021/9/23 22:01
 */
@Getter
@Setter
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
    //...省略getter/setter方法...
}
