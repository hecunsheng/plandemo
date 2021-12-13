package run.star.plan.javabase.fxfs.fx;

/**
 * 基础接口设计
 * @param <R> 返参类型
 * @param <P> 入参类型
 * @Author hecs
 * @Date 2021/11/11 20:28
 */
public interface CallBack<R,P> {
    R callBack(P p);
}
