package sun.star.plan.observer.mouse;

import sun.star.plan.observer.core.Event;

import java.lang.reflect.Method;

/**
 * 观察者和被观察者之间没有必然联系 注册的时候，才产生联系
 * @Author: hecs
 * @Date: 2018/11/3 10:56
 * @Description:
 */
public class MouseTest {
    public static void main(String[] args) {
        try {
            MouseEventCallback callback = new MouseEventCallback();
            Method method = MouseEventCallback.class.getMethod("onClick", Event.class);
            Method method1 = MouseEventCallback.class.getMethod("onDoubleClick", Event.class);
            //注入callback回调方法

            //人为的调用鼠标的单击事件
            Mouse mouse = new Mouse();
            mouse.addListener(MouseEventType.ON_CLICK, callback,method);
            mouse.addListener(MouseEventType.ON_DOUBLE_CLICK, callback,method);

            mouse.click();
            mouse.doubleClick();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
