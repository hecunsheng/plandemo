package run.star.leetcode;

/**
 * @author hecs
 * @date 2020-09-11 08:05
 */
public class VolatileExample {
    int x = 0 ;
    volatile boolean v = false;
    public void writer(){
        x = 42;
        v = true;
    }

    public void reader(){
        if (v == true){
            // 这里x会是多少呢
        }
    }

    public static void main(String[] args) {

    }
}
