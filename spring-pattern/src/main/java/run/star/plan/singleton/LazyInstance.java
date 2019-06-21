package run.star.plan.singleton;

/**
 * @Author: hecs
 * @Date: 2018/9/5 20:07
 * @Description: 懒汉式单列
 */
public class LazyInstance implements Print {

    private static LazyInstance INSTANCE = null;

    private LazyInstance() {
    }

    public static LazyInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyInstance();
        }
        return INSTANCE;
    }

    @Override
    public String leaveTheBuilding() {
        return "This is lazy singleton";
    }
}
