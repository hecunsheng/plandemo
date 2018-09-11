package sun.star.plan.factory.pay_model_01;

import sun.star.plan.factory.PayContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:19
 * @Description: 支付处理
 */
public interface PayProcessor {

    void processor(PayContext payContext);
}
