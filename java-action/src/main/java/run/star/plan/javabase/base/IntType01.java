package run.star.plan.javabase.base;

/**
 * R: break
 * @Author hecs
 * @Date 2021/10/18 15:25
 */
public class IntType01 {
    public static void main(String[] args) {
        System.out.println("进制位数：" + Integer.SIZE);
        System.out.println("最小值：" + Integer.MIN_VALUE);
        System.out.println("最大值：" + Integer.MAX_VALUE);
        System.out.println("进制位数：" + Double.SIZE);
        System.out.println("最小值：" + Double.MIN_VALUE);
        System.out.println("最大值：" + Double.MAX_VALUE);

        short s1 = 1;
//         s1 = s1 + 1 ; // 变异错误：s1自动向int类型转换
        s1 += 1;//+=运算符是java语言规定的，编译器会对它进行识别处理
        System.out.println("s1 = " + s1);


        R:
        if (true) {
            if (true) {
                System.out.println("第二层if");
                for (int i = 0; i < 5; i++) {
                    if (i == 2) {
                        System.out.println("i = " + i);
                        break R;
                    }
                    System.out.println("for:i=" + i);
                }
                if (true) {
                    System.out.println("第三层if");
                    break R;
                }
            }
            System.out.println("第一层if");
        }
    }
}
