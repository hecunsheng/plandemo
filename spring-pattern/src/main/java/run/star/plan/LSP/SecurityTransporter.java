package run.star.plan.LSP;

import org.apache.commons.lang3.StringUtils;
import sun.net.www.http.HttpClient;

/**
 * @author hecs
 * @date 2021/7/19 18:26
 */
public class SecurityTransporter extends Transporter {

    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);
        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public Response sendRequest(Request request) {
        //子类实现 符合里式替换原则
        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)) {
            request.addPayload("app-id", appId);
            request.addPayload("app-token", appToken);
        }
        return super.sendRequest(request);
    }
}
