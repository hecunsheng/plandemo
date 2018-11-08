package sun.star.plan.observer.subject;

import sun.star.plan.observer.core.EventListener;

/**
 * @Author: hecs
 * @Date: 2018/11/3 11:30
 * @Description:
 */
public class Subject extends EventListener{

    //通常的话，采用动态代理来实现监控，避免了代码侵入
    public void add(){
        System.out.println("调用新增方法");
        //TODO 采用动态代理来实现监控 根据调用的方法去动态判断触发那个事件 也可以用策略模式 事先配置好方法和触发事件的关联关系
        //这里是侵入代码
        trigger(SubjectEventType.ON_ADD);
    }

    public void remove(){
        System.out.println("调用删除方法");
        trigger(SubjectEventType.ON_REMOVE);
    }
}
