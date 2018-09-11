package sun.star.plan.factory.pay_model_01;

import sun.star.plan.factory.PayContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:24
 * @Description:
 */
public class WechatPay implements PayProcessor {

    @Override
    public void processor(PayContext payContext) {
        System.out.println("执行微信支付");
    }
}
