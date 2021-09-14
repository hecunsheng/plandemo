package run.star.plan.LSP;

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
