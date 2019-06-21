package run.star.plan.observer.core;

import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: hecs
 * @Date: 2018/11/3 09:53
 * @Description:
 */
public class EventListener {
    protected Map<Enum,Event> events = Maps.newHashMap();

    /**
     *
     * @param eventType 事件类型
     * @param target  回调目标类
     * @param callBack 回调方法
     * @Author hecs
     * @Return void
     * @Date 2018/11/3 15:00
     */
    public void addListener(Enum eventType,Object target,Method callBack){
        this.addListener(eventType,target,callBack,null);
    }
    /**
     *
     * @param eventType 事件类型
     * @param target  回调目标类
     * @param callBack 回调方法
     * @param args 方法参数
     * @Author hecs
     * @Return void
     * @Date 2018/11/3 15:00
     */
    public void addListener(Enum eventType, Object target, Method callBack,Object[] args){
        //注册事件 用反射调用这个方法
        events.put(eventType,new Event(target,callBack,args));
    }

    private void trigger(Event event){
        event.setSource(this)
             .setTime(System.currentTimeMillis());
        try {
            event.getCallBack().invoke(event.getTarget(),event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void trigger(Enum call){
        if(!this.events.containsKey(call)){return;}
        trigger(this.events.get(call).setTrigger(call.toString()));
    }
}
