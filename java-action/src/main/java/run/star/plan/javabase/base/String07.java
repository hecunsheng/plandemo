package run.star.plan.javabase.base;

/**
 * 看到下面几行代码的反应，基本能反应编程的年龄：
 * 一年：API是这样用的，没毛病；
 * 三年：StringBuffer是线程安全的，效率相对偏低；
 * 五年：默认字符数组大小是16，这里自定义字符数组的大小，
 *      如果长度不够需要扩容，所以要预估一下字符串的可能大小，减小消耗；
 *
 * 絮叨一句：Java中许多容器对象的大小默认是16，且具备动态扩容机制，这就是传说中的编程思想，
 *      在开发中照葫芦画瓢的写两段，这就是格调。
 *
 * 对于线程安全和操作和非安全操作，还有初始容量和扩容这种逻辑，都可以在源码中查看，这是进阶程序员的必备意识
 *
 * @Author hecs
 * @Date 2021/10/18 19:58
 */
public class String07 {
    public static void main(String[] args) {
        StringBuffer var = new StringBuffer(2);
        var.append("what");
        var.append("when");
        System.out.println(var);
    }
}
