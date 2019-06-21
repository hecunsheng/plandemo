package run.star.plan.strategy;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 15:59
 * @Description:
 */
public class AppleFilterByWeight implements IAppleFilter<Apple> {
    public boolean test(Apple apple) {
        return apple.getWeight() > 100;
    }
}
