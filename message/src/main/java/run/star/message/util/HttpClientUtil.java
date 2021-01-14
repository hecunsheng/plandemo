package run.star.message.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.star.message.MessageSendClient;
import run.star.message.message.MarkdownMessageBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Map;

/**
 * HttpClient 工具类，发送自定义json参数
 *
 * @author hecs
 * @version $$Id: HttpClientUtil.java, v 0.1 2018/4/8 14:46 hecs Exp $$
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager cm;

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(5000).setConnectTimeout(5000).setMaxRedirects(3).build();

    static {
        try {
            //需要通过以下代码声明对https连接支持
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,
                    new TrustSelfSignedStrategy())
                    .build();
//            HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf)
                    .build();
            //初始化连接管理器
            cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // Increase max total connection to 200
            cm.setMaxTotal(100);
            // Increase default max connection per route to 20
            cm.setDefaultMaxPerRoute(20);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }


    public static String postJsonBySSL(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        try {
            httpClient = (CloseableHttpClient) getClient();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", "application/json");
            HttpEntity entity = new StringEntity(JSON.toJSONString(params), "utf-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity, "utf-8");
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            try {
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return null;
    }

    private static HttpClient getClient() {
        return HttpClients.custom().setConnectionManagerShared(true).setConnectionManager(cm).build();
    }

    public static void main(String[] args) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String secret = "SEC4756a27b49cbf3072b54b2c1ce0a4560076d40e924b1606c8f8ea44db320276e";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        System.out.println(sign);


        Hashtable<String, Object> params = new Hashtable<>();
        params.put("msgtype", "markdown");

        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("text", "### 测试的  \n > 9度，@1825718XXXX 西北风1级，空气良89，相对温度73% \n \n > 9度，@1825718XXXX 西北风1级，空气良89，相对温度73%\n >" +
                "### 测试的  \n > 9度，@1825718XXXX 西北风1级，空气良89，相对温度73% \n \n > 9度，@1825718XXXX 西北风1级，空气良89，相对温度73%"
        );
        contentMap.put("title", "通知");

        params.put("markdown", contentMap);
        params.put("at", "{\"isAtAll\": true}");
        String res = HttpClientUtil.postJsonBySSL("https://oapi.dingtalk.com/robot/send?access_token=98dcc8f96ee1c5cc47508a869e51251864510ab9308db20d8b6f28ffa6053492" + "&timestamp=" + timestamp + "&sign=" + sign, params);
        JSONObject resObj = JSON.parseObject(res);
        System.out.println(resObj != null && resObj.get("errcode").equals(0));



        //发送微信消息提醒
//        MarkdownMessageBuilder markdownMessageBuilder = new MarkdownMessageBuilder();
//        markdownMessageBuilder.setTitle("问题反馈提醒");
//        markdownMessageBuilder.setReceiveUrl("https://oapi.dingtalk.com/robot/send?access_token=98dcc8f96ee1c5cc47508a869e51251864510ab9308db20d8b6f28ffa6053492" + "&timestamp=" + timestamp + "&sign=" + sign);
//        markdownMessageBuilder.appendContentln("反馈来源: " + 1);
//        markdownMessageBuilder.appendContentln("门店名称: " + 1);
//        markdownMessageBuilder.appendContentln("反馈渠道: " + 1);
//        markdownMessageBuilder.appendContentln("反馈类型: " + 1);
//        markdownMessageBuilder.appendContentln("反馈内容: " + 1);
//        markdownMessageBuilder.appendContentln("反馈内容: [" + 32 + "](www.baidu.com)");
//        boolean send = MessageSendClient.send(markdownMessageBuilder.build());
//        System.out.println(11);
    }
}
