package run.star.plan.javabase.fxfs.fx;

import java.util.List;

/**
 * @Author hecs
 * @Date 2021/11/11 20:30
 */
public interface ExtCallBack01 extends CallBack<Integer, List<String>> {
    @Override
    Integer callBack(List<String> list);
}
