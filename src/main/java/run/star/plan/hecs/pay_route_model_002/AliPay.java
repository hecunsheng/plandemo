package run.star.plan.hecs.pay_route_model_002;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:23
 * @Description:
 */
public class AliPay implements PayProcessor {

    @Override
    public void processor(PayContext payContext) {
        System.out.println("执行支付宝支付");
    }
}
