package run.star.plan.factory.pay_model_01;

import run.star.plan.factory.PayContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:31
 * @Description:
 */
public class PayMain {
    public static void main(String[] args) {
        PayContext payContext = new PayContext();
        PayFactory.getPayProcessor("alipay").processor(payContext);
    }
}
