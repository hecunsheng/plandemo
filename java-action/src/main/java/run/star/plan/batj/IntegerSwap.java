package run.star.plan.batj;


import java.lang.reflect.Field;

/**
 * 了解Integer的实现
 *
 * @author hecs
 * @date 2020-09-03 07:49
 */
public class IntegerSwap {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //-128~127缓存
//        Integer a = -128, b = -128;
//        System.out.println(a == b);
        System.out.println(4%2);
        Integer a = 1, b = 2;

        swap(a, b);

        System.out.println(a + " " + b);
    }

    private static void swap(Integer i1, Integer i2) throws NoSuchFieldException, IllegalAccessException {
        int temp = i1.intValue();

        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(Boolean.TRUE);
        field.setInt(i1, i2.intValue());
        field.setInt(i2, temp);
    }


}
