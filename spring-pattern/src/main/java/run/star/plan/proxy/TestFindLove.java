package run.star.plan.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestFindLove {

    public static void main(String[] args) throws IOException {
        //        new FeiHui().findLove();
        Person person = (Person) new Meipo().getInstance(new FeiHui());
        person.findLove();
        System.out.println();
        System.out.println("代理对象:" + person.getClass());

        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[] { Person.class });
        FileOutputStream os = new FileOutputStream("d:/$Proxy0.class");
        os.write(data);
        os.close();
    }
    //动态代理基本原理
    //1.拿到被代理对象的引用，JDK代理重新生成一个类，同时实现我们给的代理对象的所有接口
    //2.重新动态生成一个class字节码，然后编译
}
