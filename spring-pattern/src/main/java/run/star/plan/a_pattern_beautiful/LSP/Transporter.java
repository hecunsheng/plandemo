package run.star.plan.a_pattern_beautiful.LSP;

import sun.net.www.http.HttpClient;


/**
 * @author hecs
 * @date 2021/7/19 18:15
 */
public class Transporter {


    private HttpClient httpClient;

    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(Request request) {
        // ...use httpClient to send request
        System.out.println("执行父类");
        return null;
    }



    static class Demo {
        public void demoFunction(Transporter transporter) {
            Request request = new Request();
            //...省略设置request中数据值的代码...
            Response response = transporter.sendRequest(request);
            //...省略其他逻辑...
        }
    }

    public static void main(String[] args) {
        // 里式替换原则
        Demo demo = new Demo();
        demo.demoFunction(new SecurityTransporter(null, null, null));
    }

}
