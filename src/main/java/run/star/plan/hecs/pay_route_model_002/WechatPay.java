package run.star.plan.hecs.pay_route_model_002;

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
