package run.star.plan.hecs.pay_route_model_002.pay_spring_model_002_1;

/**
 * @Auther: hecs
 * @Date: 2018/5/16 10:38
 * @Description:
 */
public enum PayChannelEnum {
    ALI_PAY("alipay", "支付宝"), WECHAT_PAY("wechat", "微信"), DEBIT_CARD_PAY("debitpay", "借记卡");

    private String channel;

    private String desc;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PayChannelEnum(String channel, String desc) {
        this.channel = channel;
        this.desc = desc;
    }
}
