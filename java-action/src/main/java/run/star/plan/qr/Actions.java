package run.star.plan.qr; /**
/**
 * 业务操作枚举。
 * <p>
 *      <b>把业务操作名称归集到一起，主要是因为：</b>
 *      <ol>
 *      <li>以便对整个系统的业务用例进行管理</li>
 *      <li>通过此枚举，可以打印基于用例的日志</li>
 *      </ol>
 *      <pre>
 *          注意：这里的actionEnum需要搞清楚，是SysAppTemplate来使用的，还是只是单纯提供给LogFormat使用的,
 *          因为SysAppTemplate中会打印摘要日志提供给监控系统进行业务监控，请开发在新增枚举值请先搞清楚，是干什么的
 *          此外还要注意:
 *              因为本类中的code和bizType和message将会在日志统计脚本中使用到, 所以有一定的要求:
 *              1. code长度不能大于40个字节;
 *              2. message中不能包含逗号","
 *      </pre>
 * </p>
 */

public class Actions implements ActionEnumInterface {

    /** 操作编码代码 */
    private String actionCode;

    /** 功能code */
    private String bizCode;

    /** 业务类型 */
    private String bizType;

    /** 枚举代码描述 */
    private String message;

    /**
     * 构造器
     * @param code
     *              枚举代码
     * @param bizType
     *              业务类型
     * @param message
     *              枚举代码描述
     */
    public Actions(String actionCode, String bizType, String message) {
        this(actionCode, actionCode, bizType, message);
    }

    /**
     * 构造器
     * @param code
     *              枚举代码
     * @param bizType
     *              业务类型
     * @param message
     *              枚举代码描述
     */
    public Actions(String actionCode, String bizCode, String bizType, String message) {
        this.actionCode = actionCode;
        this.bizCode = bizCode;
        this.bizType = bizType;
        this.message = message;
    }


    /**
     * Getter method for property <tt>activeCode</tt>.
     *
     * @return property value of activeCode
     */
    @Override
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Getter method for property <tt>bizCode</tt>.
     *
     * @return property value of bizCode
     */
    @Override
    public String getBizCode() {
        return bizCode;
    }

    /**
     * Getter method for property <tt>bizType</tt>.
     *
     * @return property value of bizType
     */
    @Override
    public String getBizType() {
        return bizType;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    @Override
    public String getMessage() {
        return message;
    }


}
