package run.star.plan.batj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hecs
 * @date 2020-09-04 17:04
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("记录日志开始");
        Object result = method.invoke(target, args);
        System.out.println("记录日志结束");
        return result;
    }
}
