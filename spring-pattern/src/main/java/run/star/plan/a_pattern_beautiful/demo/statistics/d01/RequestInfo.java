package run.star.plan.a_pattern_beautiful.demo.statistics.d01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author hecs
 * @Date 2021/9/23 21:52
 */
@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class RequestInfo {
    private String apiName;
    private double responseTime;
    private long timestamp;
    //...省略constructor/getter/setter方法...
}
