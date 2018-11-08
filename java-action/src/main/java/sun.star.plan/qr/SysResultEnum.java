package sun.star.plan.qr;

/**
 * 处理结果类型.
 * <p>
 *      用于业务处理结果类型
 *      Y|N|E
 * </p>
 * @Author: hecs
 * @Date: 2018/10/10 11:40
 * @Description:
 */
public enum SysResultEnum {
    /** 成功 */
    Y("Y", "成功"),

    /** 失败 */
    N("N", "失败"),

    /** 异常 */
    E("E", "异常"),

    ;

    /** 处理类型 */
    private String code;

    /** 处理类型描述 */
    private String message;

    /**
     * 处理类型构造器
     *
     * @param code
     *              处理类型代码
     * @param message
     *              处理类型代码描述
     */
    private SysResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取处理结果类型代码描述
     * @return 处理类型代码描述
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取处理结果类型代码
     * @return 处理类型结果代码
     */
    public String getCode() {
        return code;
    }
}
