package run.star.plan.a_pattern_beautiful.DIP.IOC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hecs
 * @date 2021/7/21 09:39
 */
public class JunitApplication {
    private static final List<TestCase> testCases = new ArrayList<>();

    public static void register(TestCase testCase) {
        testCases.add(testCase);
    }

    public static final void main(String[] args) {
        JunitApplication.register(new UserServiceTest());
        JunitApplication.register(new LoginServiceTest());
        testCases.forEach(testCase -> {
            testCase.run();
        });
    }
}