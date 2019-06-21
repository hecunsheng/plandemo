package run.star.plan.factory.pay_model_02;

import run.star.plan.factory.PayContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:19
 * @Description: 支付处理
 */
public interface PayProcessor2 {

    Map<String, PayProcessor2> payChannelMap = new ConcurrentHashMap<>();

    void processor(PayContext payContext);
}
