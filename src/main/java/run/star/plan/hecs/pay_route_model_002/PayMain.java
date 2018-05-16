package run.star.plan.hecs.pay_route_model_002;

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
