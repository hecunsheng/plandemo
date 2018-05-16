package run.star.plan.hecs.pay_route_model_002.pay_spring_model_002_1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import run.star.plan.hecs.pay_route_model_002.PayContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:19
 * @Description: 支付处理
 */
public interface PayProcessor2 {

    static Map<String, PayProcessor2> payChannelMap = new ConcurrentHashMap<>();

    void processor(PayContext payContext);
}
