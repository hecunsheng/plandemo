package run.star.plan.singleton;

/**
 * @Author: hecs
 * @Date: 2018/9/5 15:06
 * @Description:
 */
public class SingletonTest {

    public static void main(String[] args) {
        System.out.println(HungryInstance.getInstance().leaveTheBuilding());
        System.out.println(LazyInstance.getInstance().leaveTheBuilding());
    }
}
