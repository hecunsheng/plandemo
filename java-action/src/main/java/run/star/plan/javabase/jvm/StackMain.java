package run.star.plan.javabase.jvm;

import java.util.Stack;

/**
 * @Author hecs
 * @Company 杭州一喂智能科技有限公司
 * @Date 2022/2/7 14:27
 */
public class StackMain {
    public static void main(String[] args) {
        // 入堆栈
        Stack<String> stack = new Stack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        int stackSize = stack.size();
        int loop = 0;
        // 根据栈大小，不断出栈
        while (loop < stackSize) {
            System.out.println(stack.pop());
            System.out.println(stack);
            loop++;
        }
    }
}
