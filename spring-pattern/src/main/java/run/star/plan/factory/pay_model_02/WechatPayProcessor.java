package run.star.plan.factory.pay_model_02;

import org.springframework.stereotype.Service;
import run.star.plan.factory.PayContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/16 10:42
 * @Description:
 */
@Service
public class WechatPayProcessor extends AbstractPayProcessor {
    @Override
    public boolean preCheckedContext(PayContext payContext) {
        if (payContext.getCents() <= 0) {
            payContext.setMsg("支付金额不能小于等于0");
            return false;
        }
        return true;
    }

    @Override
    public void doPay(PayContext payContext) {
        System.out.println("执行微信支付,请求参数:" + payContext);
    }

    @Override
    public String getPayChannel() {
        return PayChannelEnum.WECHAT_PAY.getChannel();
    }
}
