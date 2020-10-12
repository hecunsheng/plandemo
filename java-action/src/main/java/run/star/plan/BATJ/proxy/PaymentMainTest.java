package run.star.plan.BATJ.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author hecs
 * @date 2020-09-04 16:42
 */
public class PaymentMainTest {

    public static void main(String[] args) {
//        Payment payment = new ThirdChannelPayment();
//        System.out.println(payment.doPay("hecs"));

        //静态代理
//        Payment payment = new ThirdPaymentProxy();
//        payment.doPay("hecs");

        //动态代理
//        DynamicProxy dynamicProxy = new DynamicProxy();
//        Payment payment = new ThirdChannelPayment();
//        Payment p1 = (Payment) dynamicProxy.bind(payment);
//        p1.doPay("hecs");

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "code");

        //cglib动态代理
        CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy();
        ThirdChannelPayment payment = (ThirdChannelPayment) cglibDynamicProxy.getIntance(new ThirdChannelPayment());
        payment.doPay("hecs");

        byte[] classFiles = ProxyGenerator.generateProxyClass("$Proxy0", ThirdChannelPayment.class.getClasses());
        String path = "/Users/hecs/IdeaProjects/plandemo/java-action/src/main/java/run/star/plan/BATJ/proxy/PaymentProxy.class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFiles);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
