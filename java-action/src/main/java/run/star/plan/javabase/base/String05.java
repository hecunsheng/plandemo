package run.star.plan.javabase.base;

/**
 * String对象形参传递到方法里的时候,实际上传递的是引用的拷贝。
 * 案例中改变的是var1引用的拷贝,方法结束执行结束，形参var1被销毁, 原对象的引用保持不变。
 * 数组作为参数传递时传递是数组在内存中的地址值，这样直接找到数组在内存中的位置。
 * @Author hecs
 * @Date 2021/10/18 19:12
 */
public class String05 {
    String var1 = "hello";
    int[] intArr = {1, 2, 3};

    public static void main(String[] args) {
        String05 objStr = new String05();
        objStr.change(objStr.var1, objStr.intArr);
        // hello  4
        System.out.println(objStr.var1);
        System.out.println(objStr.intArr[2]);
    }

    public void change(String var1, int[] intArr) {
        var1 = "world";
        intArr[2] = 4;
    }
}
