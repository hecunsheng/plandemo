package run.star.plan.qr;

/**
 * 处理类型.
 * <p>
 *      用于业务处理类型
 *      例如：<br>
 *      <ol>
 *      <li>如果业务逻辑需要事务控制，，则可以使用<code>SysHandlerType.TRAN</code></li>
 *      <li>如果业务逻辑不需要事务控制，则可以使用<code>SysHandlerType.NONE</code></li>
 *      </ol>
 * </p>
 * @Author: hecs
 * @Date: 2018/10/10 14:19
 * @Description:
 */
public enum SysHandlerType {
    /** 不需要事务控制 */
    NONE("NONE", "不需要事务控制"),

    /** 事务 */
    TRAN("TRAN", "需要使用事务"),

    /** 特殊处理 */
    SPECIAL("SPECIAL", "特殊处理"),

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
    private SysHandlerType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取处理类型代码描述
     * @return 处理类型代码描述
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取处理类型代码
     * @return 处理类型代码
     */
    public String getCode() {
        return code;
    }
}
