package run.star.plan.observer.subject;

import run.star.plan.observer.core.Event;

/**
 * @Author: hecs
 * @Date: 2018/11/3 11:34
 * @Description:
 */
public class ObServer {

    public void advice(Event event){
        System.out.println("=========触发事件,打印日志========\n" + event);
    }
}
