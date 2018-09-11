package sun.star.plan.strategy;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 15:59
 * @Description:
 */
public class AppleFilterByGreenColor implements IAppleFilter<Apple> {
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
