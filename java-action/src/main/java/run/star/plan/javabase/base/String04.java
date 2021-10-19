package run.star.plan.javabase.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化操作
 * 在日常开发中，字符串的格式不会都满足业务要求，通常就需要进行指定格式化操作。
 * 案例演示应用场景：Redis缓存Key生成，日期类型转换，超长浮点数的截取。
 * @Author hecs
 * @Date 2021/10/18 19:03
 */
public class String04 {
    public static void main(String[] args) {
        // 指定位置拼接字符串
        String var1 = formatStr("cicada", "smile", "xxx", "222");
        System.out.println("var1=" + var1);
        // 格式化日期：2020-03-07
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(format.format(date));
        // 浮点数：此处会四舍五入
        double num = 3.14159;
        System.out.print(String.format("浮点类型：%.2f %n", num));
    }

    public static String formatStr(String... var) {
        return String.format("key:%s:route:%s %s %s", var);
    }
}
