package run.star.plan.observer.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

/**
 * @Author: hecs
 * @Date: 2018/11/3 09:38
 * @Description:
 */
@Data
@Accessors(chain = true)
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

    private Object args;

    public Event(Object target, Method callBack,Object args) {
        this.target = target;
        this.callBack = callBack;
        this.args = args;
    }
}
