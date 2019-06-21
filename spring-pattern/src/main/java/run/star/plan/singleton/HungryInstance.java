package run.star.plan.singleton;

/**
 * @Author: hecs
 * @Date: 2018/9/5 14:31
 * @Description: 饿汉式单例
 * 在实例使用之前，不管用不用，先实例化出来，避免线程安全问题
 */
public class HungryInstance implements Print {

    private static final HungryInstance INSTANCE = new HungryInstance();

    private HungryInstance() {
    }

    public static HungryInstance getInstance() {
        return INSTANCE;
    }

    public String leaveTheBuilding() {
        return "This is hungry singleton";
    }
}
