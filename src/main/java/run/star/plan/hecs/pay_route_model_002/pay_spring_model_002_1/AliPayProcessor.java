package run.star.plan.hecs.pay_route_model_002.pay_spring_model_002_1;

import org.springframework.stereotype.Service;

import run.star.plan.hecs.pay_route_model_002.PayContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/16 10:43
 * @Description:
 */
@Service
public class AliPayProcessor extends AbstractPayProcessor {
    @Override
    public boolean preCheckedContext(PayContext payContext) {
        if(payContext.getCents()<=0){
            payContext.setMsg("支付金额不能小于等于0");
            return false;
        }
        return true;
    }

    @Override
    public void doPay(PayContext payContext) {
        System.out.println("执行支付宝支付,请求参数:"+payContext);
    }

    @Override
    public String getPayChannel() {
        return PayChannelEnum.ALI_PAY.getChannel();
    }
}
