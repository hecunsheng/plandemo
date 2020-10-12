package run.star.plan.BATJ.proxy;

/**
 * @author hecs
 * @date 2020-09-04 16:39
 */
public class ThirdChannelPayment implements Payment {


    @Override
    public String doPay(String uid) {
        System.out.println("uid->" + uid + "发起支付");
        return "success";
    }
}
