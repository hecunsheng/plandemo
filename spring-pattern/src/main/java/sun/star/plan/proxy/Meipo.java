package sun.star.plan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Meipo implements InvocationHandler {
    private Person person;

    public Object getInstance(Person person) {
        this.person = person;
        Class clazz = person.getClass();
        System.out.println("被代理对象:" + clazz);
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(this.person.toString());
        method.invoke(this.person);
        //        this.person.findLove();
        return null;
    }
}
