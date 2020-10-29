package run.star.message.exception;

/**
 * 消息异常基类
 *
 * @author hecs
 * @version $$Id: MessageException.java, v 0.1 2018/7/13 14:14 hecs Exp $$
 */
public class MessageException extends RuntimeException {

    private static final long serialVersionUID = -9012572395235006132L;

    public MessageException() {
        super();
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }
}
