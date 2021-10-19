package run.star.plan.javabase.base;

/**
 * ==：对于基本类型，比较的是值，对于引用类型，比较的是地址的值；
 * equals：该方法源自Object中一个最基础的通用方法，在Object的方法中使用==判断地址的值，
 * 只是到了String类中进行了重写，用于字符内容的比较，该方法在继承关系中的变化，
 * 追踪JDK源码，变化非常清楚。
 * @Author hecs
 * @Date 2021/10/18 17:53
 */
public class String02 {
    public static void main(String[] args) {
        String var1 = "cicada";
        String var2 = "cicada";
        // true;true
        System.out.println((var1 == var2) + ";" + var1.equals(var2));
        String var3 = new String("cicada");
        String var4 = new String("cicada");
        // false;true
        System.out.println((var3 == var4) + ";" + var3.equals(var4));
        // false;true
        System.out.println((var1 == var4) + ";" + var2.equals(var4));
        String var5 = "ci" + "cada";
        // true;true
        System.out.println((var1 == var5) + ";" + var5.equals(var4));
        String var6 = new String02().getVar6();
        // true;true
        System.out.println((var1 == var6) + ";" + var6.equals(var4));
    }

    public String getVar6() {
        return "cicada";
    }
}
