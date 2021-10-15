package run.star.plan.a_pattern_beautiful.demo.statistics.d01;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author hecs
 * @Date 2021/9/23 21:44
 */
public class MetricsCollector {
    //基于接口, 而非实现编程
    private MetricsStorage metricsStorage;

    //依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    //用一个函数代替了最小原型中的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}

