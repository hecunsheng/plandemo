package run.star.plan.a_pattern_beautiful.ISP.b;

import java.util.Collection;

/**
 * @author hecs
 * @date 2021/7/19 21:02
 */
public class Statistics {
    private Long max;
    private Long min;
    private Long average;
    private Long sum;
    private Long percentile99;
    private Long percentile999;
    //...省略constructor/getter/setter等方法...

    public Statistics count(Collection<Long> dataSet) {
        Statistics statistics = new Statistics();
        //...省略计算逻辑...
        return statistics;
    }


    // ...省略其他统计函数...
    public Long max(Collection<Long> dataSet) {
        return null;
    }
    public Long min(Collection<Long> dataSet) {
        return null;
    }
    public Long average(Collection<Long> dataSet) {
        return null;
    }

}
