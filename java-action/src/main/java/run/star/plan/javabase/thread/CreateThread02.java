package run.star.plan.javabase.thread;

/**
 * @Author hecs
 * @Date 2021/12/16 17:31
 */
public class CreateThread02 {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread02(),"custom Thread Name");
        thread.start();
    }
}
