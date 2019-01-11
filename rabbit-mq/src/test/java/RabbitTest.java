import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import star.plan.demo.annotation.Order;
import star.plan.demo.producer.MessageProducer;

/**
 * @Author: qingshan
 * @Date: 2018/9/21 10:42
 * @Description: 咕泡学院，只为更好的你
 */
public class RabbitTest {
    private ApplicationContext context = null;

    @Test
    public void sendMessage() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MessageProducer messageProducer = (MessageProducer) context.getBean("messageProducer");
        Order order = new Order();
        order.setId("1");
        order.setMessageId("99888776655");
        order.setOrderNo("TWL1111111111");
        order.setSaleAmount(99.888);
        messageProducer.sendMessage(order);


//        int k = 100;
//        while (k > 0) {
//            messageProducer.sendMessage("第" + k + "次发送的消息");
//            k--;
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
