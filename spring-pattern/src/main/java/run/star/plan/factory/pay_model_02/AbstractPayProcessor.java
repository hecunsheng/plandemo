package run.star.plan.factory.pay_model_02;

import org.springframework.stereotype.Service;
import run.star.plan.factory.PayContext;

import javax.annotation.PostConstruct;

/**
 * @Auther: hecs
 * @Date: 2018/5/16 10:27
 * @Description:
 */
@Service
public abstract class AbstractPayProcessor implements PayProcessor2 {

    @PostConstruct
    public void init() {
        payChannelMap.put(getPayChannel(), this);
    }

    @Override
    public void processor(PayContext payContext) {
        if (!preCheckedContext(payContext)) {
            payContext.setMsg("参数校验失败");
            return;
        }
        doPay(payContext);
    }

    //预检查上下文 对参数进行校验
    public abstract boolean preCheckedContext(PayContext payContext);

    /**
     * 执行支付方法
     * @param payContext
     */
    public abstract void doPay(PayContext payContext);

    /**
     * 支付渠道编码
     * @return
     */
    public abstract String getPayChannel();
}
