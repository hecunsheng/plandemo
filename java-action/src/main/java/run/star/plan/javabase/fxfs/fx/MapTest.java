package run.star.plan.javabase.fxfs.fx;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author hecs
 * @Date 2021/11/11 20:17
 */
public class MapTest {
    public static void main(String[] args) {
        Map map = Maps.newHashMap();
        System.out.println(map.put(1, 2));
        System.out.println(map.putIfAbsent(1, 2));
        System.out.println(map.putIfAbsent(2, 1));
    }
}
