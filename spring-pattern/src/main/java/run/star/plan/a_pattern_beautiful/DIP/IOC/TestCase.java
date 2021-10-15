package run.star.plan.a_pattern_beautiful.DIP.IOC;

/**
 * @author hecs
 * @date 2021/7/21 09:39
 */
public abstract class TestCase {

    public void run() {
        if (doTest()) {
            System.out.println("Test succeed.");
        } else {
            System.out.println("Test failed.");
        }
    }

    public abstract boolean doTest();

}
