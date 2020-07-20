package run.star.plan.log;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author hecs
 * @date 2020-07-13 11:30
 */
public class LogTest {
    public static void main(String[] args) {
        List<SkuRo> skuRoList = Lists.newArrayList();
        SkuRo skuRo = new SkuRo();
        skuRo.setId(1L);
        skuRo.setSkuName("规格");
        SkuRo skuRo1 = new SkuRo();
        skuRo1.setId(2L);
        skuRo1.setSkuName("保质期");
        skuRoList.add(skuRo);
        skuRoList.add(skuRo1);


        List<SkuRo> skuRoList1 = Lists.newArrayList();
        SkuRo skuRo3 = new SkuRo();
        skuRo3.setId(3L);
        skuRo3.setSkuName("品牌");
        skuRoList1.add(skuRo3);

        Person user1 = new Person(1L, "张三", 21, true, null, Lists.newArrayList(1, 2, 3, 4));
        System.out.println("user1 = " + JSON.toJSONString(user1));
        Person user2 = new Person(1L, "李四", 24, false, skuRoList1, Lists.newArrayList(5, 6, 7, 8));
        System.out.println("user2 = " + JSON.toJSONString(user2));

        try {
            UnityLog log = user2.createLog(BaseLog.DISABLE, user1);
            System.out.println("log = " + JSON.toJSONString(log));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
