package run.star.plan.observer.subject;


import run.star.plan.observer.core.Event;

import java.lang.reflect.Method;

/**
 * @Author: hecs
 * @Date: 2018/11/3 11:36
 * @Description:
 */
public class ObServerTest {

    public static void main(String[] args) {
        try {
            //观察者
            ObServer observer = new ObServer();
            Method method = ObServer.class.getMethod("advice", Event.class);
            //注入callback回调方法

            //注入监听
            Subject subject = new Subject();
            subject.addListener(SubjectEventType.ON_ADD,observer,method);
            subject.addListener(SubjectEventType.ON_EDIT,observer,method);
            subject.addListener(SubjectEventType.ON_REMOVE,observer,method);
            subject.addListener(SubjectEventType.ON_QUERY,observer,method);

            subject.add();
            subject.remove();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
