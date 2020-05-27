package run.star.leetcode;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hecs
 * @date 2020-05-26 13:11
 */
public class TwoSum {

    public static Map<String, Object> cacheMap = new HashMap<>(2);

    /**
     * 不知道two sum是什么梗的，笔者和你介绍一下：
     * two sum是刷题网站leetcode序号为1的题，也就是大多人的算法入门的第一题。
     * 常常被人调侃，有算法面的公司，被面试官钦定了，合的来。那就来一道two sum走走过场。
     * 问题内容是：给定一个数组，给定一个数字。返回数组中可以相加得到指定数字的两个索引。
     * 比如：给定nums = [2, 7, 11, 15], target = 9
     * 那么要返回 [0, 1]，因为2 + 7 = 9
     * 这道题的优解是，一次遍历+HashMap
     * <p>
     * 作者：Vt
     * 链接：https://juejin.im/post/5e927e27f265da47c8012ed9
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    //spring解决循环依赖

    /**
     * 首先，Spring内部维护了三个Map，也就是我们通常说的三级缓存
     * 笔者翻阅Spring文档倒是没有找到三级缓存的概念，可能也是本土为了方便理解的词汇。
     * 在Spring的DefaultSingletonBeanRegistry类中，你会赫然发现类上方挂着这三个Map：
     * <p>
     * singletonObjects  它是我们最熟悉的朋友，俗称“单例池”“容器”，缓存创建完成单例Bean的地方。
     * singletonFactories  映射创建Bean的原始工厂
     * earlySingletonObjects 映射Bean的早期引用，也就是说在这个Map里的Bean不是完整的，
     * 甚至还不能称之为“Bean”，只是一个Instance.
     * <p>
     * 后两个Map其实是“垫脚石”级别的，只是创建Bean的时候，用来借助了一下，创建完成就清掉了。
     * 所以笔者前文对“三级缓存”这个词有些迷惑，可能是因为注释都是以Cache of开头吧。
     * 为什么成为后两个Map为垫脚石，假设最终放在singletonObjects的Bean是你想要的一杯“凉白开”。
     * 那么Spring准备了两个杯子，即singletonFactories和earlySingletonObjects来回“倒腾”几番，
     * 把热水晾成“凉白开”放到singletonObjects中。
     */

    static class A {
        private B b;


    }

    static class B {
        private A a;
    }

    public static void main(String[] args) {
        //two sum
        int[] nums = {2, 7, 9, 15};
        int target = 11;
        System.out.println(Arrays.toString(twoSum(nums, target)));


        // spring解决循环依赖
        Class[] classes = {A.class, B.class};
        // 假装项目初始化实例化所有bean        
        for (Class aClass : classes) {
            getBean(aClass);
        }
        // check
        System.out.println(getBean(A.class).b == getBean(B.class));
        System.out.println(getBean(B.class).a == getBean(A.class));
    }

    @SneakyThrows
    private static <T> T getBean(Class<T> beanClass) {
        // 类名小写 简单代替bean的命名规则
        String simpleBeanName = beanClass.getSimpleName().toLowerCase();
        // 如果已经是一个bean，则直接返回
        if (cacheMap.containsKey(simpleBeanName)) {
            return (T) cacheMap.get(simpleBeanName);
        }
        // 将对象本身实例化
        Object object = beanClass.getDeclaredConstructor().newInstance();
        // 放入缓存
        cacheMap.put(simpleBeanName, object);
        // 把所有字段当成需要注入的bean，创建并注入到当前bean中
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(Boolean.TRUE);
            // 获取需要注入字段的class
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            // 如果需要注入的bean，已经在缓存Map中，那么把缓存Map中的值注入到该field即可
            // 如果缓存没有 继续创建
            Object vObj = cacheMap.containsKey(fieldBeanName) ? cacheMap.get(fieldBeanName) : getBean(fieldClass);
            // 属性填充完成，返回
            field.set(object, vObj);
        }
        return (T) object;
    }
    //个人认为并不能理解为缓存，缓存的最终目的是为了解决性能，
    //而这三个map的目的更多的是标识一个bean的创建状态。
    // 比如在单例池中的表示bean已经完整的被创建，
    // 在单例工厂中的表示bean正在被创建，
    // 在early中的表示已经被创建但不完整

    //QA:为什么不能直接放到earlySingletonObjects,而需要一个singletonFactories?
    // 这是一个非常经典的问题，
    // 第一点首先要知道earlySingletonObjects和singletonFactories没有必然的联系，
    // 不循环依赖的场景也要走singletonFactories的。
    // 至于为什么是工厂而不是直接的引用，因为Spring自然要具有高拓展性。
    // 有一个最直观的方式，你可以在IDE中对ObjectFactory的getObject方法，
    // 使用一个“查看所有实现”的快捷键，看完这些实现自然就知道为什么要用工厂了
}
