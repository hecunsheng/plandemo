package run.star.plan.hecs.pay_route_model_002;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:27
 * @Description:
 */
public class PayFactory {
    public static PayProcessor getPayProcessor(String type) {
        switch (type) {
            case "alipay":
                return new AliPay();
            case "wechatpay":
                return new WechatPay();
            default:
                throw new RuntimeException("未找到合适的支付渠道");
        }
    }
}
