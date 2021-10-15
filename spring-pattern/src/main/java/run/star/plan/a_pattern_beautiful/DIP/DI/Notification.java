package run.star.plan.a_pattern_beautiful.DIP.DI;

/**
 * @author hecs
 * @date 2021/7/27 16:58
 */
public class Notification {

    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }


    public void setMessage(String cellPhone, String message) {
        this.messageSender.send(cellPhone, message);
    }

    public static void main(String[] args) {
        MessageSender messageSender = new SmsSender();
        Notification notification = new Notification(messageSender);
        notification.setMessage("111", "hi 111");
    }
}
