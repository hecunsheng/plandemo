package run.star.plan.javabase.thread;

/**
 * @Author hecs
 * @Date 2021/12/16 17:32
 */
public class MyThread02 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
