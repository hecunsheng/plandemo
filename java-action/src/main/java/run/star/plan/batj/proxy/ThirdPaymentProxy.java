package run.star.plan.batj.proxy;

/**
 * @author hecs
 * @date 2020-09-04 16:57
 */
public class ThirdPaymentProxy implements Payment {

    private Payment payment = new ThirdChannelPayment();

    private PaymentLogger paymentLogger = new PaymentLogger();

    @Override
    public String doPay(String uid) {
        paymentLogger.recordLog(uid);
        return payment.doPay(uid);
    }
}
