package run.star.plan.factory.pay_model_02;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import run.star.plan.factory.PayContext;

import java.util.Map;

/**
 * @Auther: hecs
 * @Date: 2018/5/16 10:54
 * @Description:
 */
@Configurable
@ComponentScan("run.star.plan.factory.pay_model_02")
public class PayMethodMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            PayMethodMain.class);
        PayContext payContext = new PayContext();
        payContext.setCents(1);
        Map<String, PayProcessor2> payChannelMap = PayProcessor2.payChannelMap;
        payChannelMap.get(PayChannelEnum.DEBIT_CARD_PAY.getChannel()).processor(
            payContext);
    }
}
