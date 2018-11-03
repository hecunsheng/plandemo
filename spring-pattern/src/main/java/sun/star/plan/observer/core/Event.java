package sun.star.plan.observer.core;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Author: hecs
 * @Date: 2018/11/3 09:38
 * @Description:
 */
@Data
public class Event {
    //事件源
    private Object source;
    //通知目标
    private Object target;
    //回调
    private Method callBack;
    //触发
    private String trigger;
    //时间点
    private long time;

    public Event(Object target, Method callBack) {
        this.target = target;
        this.callBack = callBack;
    }

//    Event setSource(Object source) {
//        this.source = source;
//        return this;
//    }

    Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }


//    Event setTime(long time) {
//        this.time = time;
//        return this;
//    }


}
