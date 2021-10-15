package run.star.plan.a_pattern_beautiful.demo.statistics.d01;

/**
 * @Author hecs
 * @Company 杭州一喂智能科技有限公司
 * @Date 2021/9/23 22:03
 */
public class DemoRun {
    public static void main(String[] args) {

        MetricsStorage storage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(60, 60);

//                EmailReporter emailReporter = new EmailReporter(storage);
//                emailReporter.addToAddress("wangzheng@xzg.com");
//                emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
