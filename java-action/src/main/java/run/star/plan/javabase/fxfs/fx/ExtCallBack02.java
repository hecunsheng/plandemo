package run.star.plan.javabase.fxfs.fx;

import java.util.Map;

/**
 * @Author hecs
 * @Date 2021/11/11 20:30
 */
public interface ExtCallBack02 extends CallBack<Boolean, Map<Integer, String>> {
    @Override
    Boolean callBack(Map<Integer, String> map);
}
