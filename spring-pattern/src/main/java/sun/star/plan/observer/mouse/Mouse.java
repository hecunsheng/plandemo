package sun.star.plan.observer.mouse;

import sun.star.plan.observer.core.EventListener;

/**
 * 被观察者
 * @Author: hecs
 * @Date: 2018/11/3 10:41
 * @Description:
 */
public class Mouse extends EventListener{
    public void click(){
        System.out.println("鼠标单击事件");
        this.trigger(MouseEventType.ON_CLICK);
    }
    public void doubleClick(){
        System.out.println("鼠标双击事件");
        this.trigger(MouseEventType.ON_DOUBLE_CLICK);
    }
    public void up(){
        System.out.println("鼠标弹起事件");
        this.trigger(MouseEventType.ON_UP);
    }
    public void down(){
        System.out.println("鼠标按下事件");
        this.trigger(MouseEventType.ON_DOWN);
    }
    public void wheel(){
        System.out.println("鼠标滚动事件");
        this.trigger(MouseEventType.ON_WHEEL);
    }
    public void move(){
        System.out.println("鼠标移动事件");
        this.trigger(MouseEventType.ON_MOVE);
    }
    public void over(){
        System.out.println("鼠标悬停事件");
        this.trigger(MouseEventType.ON_OVER);
    }
}
