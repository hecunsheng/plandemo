package sun.star.plan.observer.mouse;

import sun.star.plan.observer.core.Event;

/**
 * 观察者 回调响应的逻辑
 * @Author: hecs
 * @Date: 2018/11/3 10:54
 * @Description:
 */
public class MouseEventCallback {
    public void onClick(Event event){
        System.out.println("=======触发鼠标单击事件========\n" + event);
        System.out.println("这是鼠标单击以后要执行的逻辑");
    }
    public void onDoubleClick(Event event){
        System.out.println("=======触发鼠标双击事件========\n" + event);
        System.out.println("这是鼠标双击以后要执行的逻辑");
    }
    public void onUp(Event event){
        System.out.println("=======触发鼠标弹起事件========\n" + event);
    }
    public void onDown(Event event){
        System.out.println("=======触发鼠标按下事件========\n" + event);
    }
    public void onMove(Event event){
        System.out.println("=======触发鼠标移动事件========\n" + event);
    }
    public void onWheel(Event event){
        System.out.println("=======触发鼠标滚动事件========\n" + event);
    }
    public void onOver(Event event){
        System.out.println("=======触发鼠标悬停事件========\n" + event);
    }
}
