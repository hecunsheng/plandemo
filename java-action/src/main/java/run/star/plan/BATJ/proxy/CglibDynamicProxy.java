package run.star.plan.BATJ.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author hecs
 * @date 2020-09-04 17:31
 */
public class CglibDynamicProxy implements MethodInterceptor {

    Object target;

    Object getIntance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();//加强器
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理记录日志开始");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib代理记录日志结束");
        return result;
    }
}
