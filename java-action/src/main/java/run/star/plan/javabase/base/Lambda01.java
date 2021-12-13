package run.star.plan.javabase.base;

/**
 * 函数式编程也减少很多临时变量的创建，代码风格也变的简洁清楚
 * FunctionalInterface标记在接口上，表示该接口是函数式接口，并且该接口只包含一个抽象方法
 * Lambda表达式最直观的作用就是使得代码变得异常简洁，并且可以作为参数传递
 * @Author hecs
 * @Date 2021/10/19 11:03
 */
public class Lambda01 {

    interface LambdaOpera {
        int operation(int a, int b);
    }

    public static void main(String[] args) {
        LambdaOpera lambdaOpera = new LambdaOpera() {
            @Override
            public int operation(int a, int b) {
                return a * b;
            }
        };
        System.out.println(lambdaOpera.operation(3, 2));
        LambdaOpera lambdaOpera01 = (int a, int b) -> a * b;
        LambdaOpera lambdaOpera02 = (a, b) -> a * b;
        LambdaOpera lambdaOpera03 = (a, b) -> a - b;
        System.out.println(lambdaOpera01.operation(3, 2));
        System.out.println(lambdaOpera02.operation(3, 2));
        System.out.println(lambdaOpera03.operation(3, 2));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println(i);
                }
            }
        }).start();
        // 对比 Lambda 方式
        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println(i);
            }
        }).start();

    }
}
