package run.star.plan.hecs.celue_model;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 15:49
 * @Description:
 */
public interface IAppleFilter<T> {
    boolean test(T t);
}
