package run.star.plan.DIP.DI;

/**
 * @author hecs
 * @date 2021/7/27 17:12
 */
public class InboxSender implements MessageSender {
    @Override
    public void send(String cellPhone, String message) {
        System.out.println(String.format("inbox send cellPhone=%s, message=%s", cellPhone, message));
    }
}
