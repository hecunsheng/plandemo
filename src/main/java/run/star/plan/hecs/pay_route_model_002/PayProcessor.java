package run.star.plan.hecs.pay_route_model_002;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:19
 * @Description: 支付处理
 */
public interface PayProcessor {

    void processor(PayContext payContext);
}
