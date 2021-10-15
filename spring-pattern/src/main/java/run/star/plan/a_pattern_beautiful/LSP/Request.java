package run.star.plan.a_pattern_beautiful.LSP;

/**
 * @author hecs
 * @date 2021/7/19 18:22
 */
public class Request {
    private String appId;
    private String appToken;

    public void addPayload(String appId, String appToken) {
        this.appId = appId;
        this.appToken = appToken;
    }

}
