package run.star.plan.a_pattern_beautiful.demo.statistics.d01;

import java.util.List;
import java.util.Map;

/**
 * @Author hecs
 * @Date 2021/9/23 21:48
 */
public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
