package run.star.plan.javabase.base;

/**
 * @Author hecs
 * @Date 2021/10/18 15:54
 */
public class String01 {
    public static void main(String[] args) {
        //两种方式，常量和创建对象。
        String var1 = "cicada" ; //声明的是一个常量，显然是放在常量池中。
        String var2 = new String("smile") ; //创建字符串对象，对象存放在堆内存中。
        System.out.println();
    }
}
