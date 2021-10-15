package run.star.plan.a_pattern_beautiful.demo.statistics.d01;

import java.util.List;
import java.util.Map;

/**
 * @Author hecs
 * @Date 2021/9/23 21:58
 */
public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
