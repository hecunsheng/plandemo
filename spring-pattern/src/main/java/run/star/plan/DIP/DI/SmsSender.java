package run.star.plan.DIP.DI;

/**
 * @author hecs
 * @date 2021/7/27 17:10
 */
public class SmsSender implements MessageSender {
    @Override
    public void send(String cellPhone, String message) {
        System.out.println(String.format("sms send cellPhone=%s, message=%s", cellPhone, message));
    }
}
