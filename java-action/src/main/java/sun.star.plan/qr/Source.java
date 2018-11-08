package sun.star.plan.qr;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 系统源枚举
 * <p>用于列举团队中的各个系统的基础信息</p>
 *
 * @Author: hecs
 * @Date: 2018/10/10 14:12
 * @Description:
 */
public enum Source {
    // Superion - 基础平台 200
    MARKETCENTER(200, "营销中心"),
    /** 不规范的code */
    CRM(201, "销售管理系统"),
    GOODSCENTER(202, "商品中心"),
    PAYCENTER(204, "支付中心"),
    MEMBERCENTER(205, "用户中心"),
    MERCHANTCENTER(206, "商户中心"),
    ORDERCENTER(207, "订单中心"),
    GROWTH(208, "成长体系");

    private int code;
    private String desc;

    Source(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    //获取系统码
    public int getCode() {
        return code;
    }

    //获取系统中文名
    public String getDesc() {
        return desc;
    }

    //根据APP_NAME获取Source实例
    public static Source lookup(String appName) {
        checkArgument(isNotBlank(appName), "appName must not be blank");
        return Source.valueOf(appName.toUpperCase());
    }
}
