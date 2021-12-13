package run.star.plan.javabase.fxfs.fx;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Author hecs
 * @Date 2021/11/11 20:33
 */
public class CallBackTest {
    public static void main(String[] args) {
        ExtCallBack01 extCallBack01 = list -> {
            list.add("hello");
            return list.size();
        };
        System.out.println(extCallBack01.callBack(Lists.newArrayList()));

        ExtCallBack02 extCallBack02 = map -> {
            map.put(1, "hello");
            return map.size() > 0;
        };

        System.out.println(extCallBack02.callBack(Maps.newHashMap()));
    }
}
