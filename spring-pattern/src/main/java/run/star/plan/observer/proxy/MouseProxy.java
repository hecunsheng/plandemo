package run.star.plan.observer.proxy;

import com.google.common.collect.Maps;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import run.star.plan.observer.core.Event;
import run.star.plan.observer.core.EventListener;
import run.star.plan.observer.mouse.MouseEventCallback;
import run.star.plan.observer.mouse.MouseEventType;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: hecs
 * @Date: 2018/11/6 16:40
 * @Description:
 */
public class MouseProxy extends EventListener implements MethodInterceptor{

    private static Map<String,Enum> methodMap = Maps.newConcurrentMap();
    private static Map<String,String> methodNameMap = Maps.newHashMap();
    static {
        methodMap.put("click", MouseEventType.ON_CLICK);
        methodMap.put("doubleClick", MouseEventType.ON_DOUBLE_CLICK);
        methodMap.put("down", MouseEventType.ON_DOWN);
        methodMap.put("move", MouseEventType.ON_MOVE);
        methodMap.put("over", MouseEventType.ON_OVER);
        methodMap.put("up", MouseEventType.ON_UP);
        methodMap.put("wheel", MouseEventType.ON_WHEEL);
    }
    static {
        methodNameMap.put("click", MouseEventType.ON_CLICK.name());
        methodNameMap.put("doubleClick", MouseEventType.ON_DOUBLE_CLICK.name());
        methodNameMap.put("down", MouseEventType.ON_DOWN.name());
        methodNameMap.put("move", MouseEventType.ON_MOVE.name());
        methodNameMap.put("over", MouseEventType.ON_OVER.name());
        methodNameMap.put("up", MouseEventType.ON_UP.name());
        methodNameMap.put("wheel", MouseEventType.ON_WHEEL.name());
    }

    public Object getInstance(Class<?> classz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();
        Enum mouseEnum = methodMap.get(methodName);
        MouseEventCallback mouseEventCallback = new MouseEventCallback();
        //这里观察者和被观察者的方法名相同  如果方法不相同  还需要加一个方法名映射
        Method callBackMethod = mouseEventCallback.getClass().getMethod(methodName, Event.class);
        //注册事件  绑定观察者
        //如果需要通知多个观察者 则需要绑定多个Listener
        addListener(mouseEnum,mouseEventCallback,callBackMethod);
        //执行方法
        methodProxy.invokeSuper(o,objects);
        //通知观察者
        trigger(mouseEnum);
        return null;
    }
}
