package sun.star.plan.qccr; /**
/**
 *
 * 系统应用异常。
 *
 * <p>
 *      在使用系统应用模板的业务逻辑中，当遇到业务校验不通过或捕获到业务系统，可以采用2种方式来处理。第一个就是返回失败的错误结果；
 *      第二个就是抛出系统应用异常SysAppException，
 * </p>
 *
 */
public class SysAppException extends RuntimeException {

    private static final long serialVersionUID = -3708111590848157964L;

    /**  异常返回结果 **/
    private Object           data;
    /**
     * 错误码
     */
    private StateCode         stateCode;

    /**
     * 扩展描述,
     */
    private String            extDescribe;

    /**
     *  App 展示信息，对应 Result.appMsg
     */
    private String            appMsg;

    /**
     * 系统应用异常构造器
     * @param stateCode
     *              业务处理结果枚举
     */
    public SysAppException(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    /**
     *
     * @param stateCode
     * @param extDescribe
     */
    public SysAppException(StateCode stateCode, String extDescribe) {
        this.stateCode = stateCode;
        this.extDescribe = extDescribe;
    }

    /**
     *  异常信息返回， 不带返回数据
     * @param stateCode
     * @param ext   1 Result.extInfo ,日常描述，  2.Result.appMsg 用于app文案透出
     */
    public SysAppException(StateCode stateCode, String... ext) {
        this.stateCode = stateCode;
        if (ext.length == 1){
            this.extDescribe = ext[0];
        }else if (ext.length > 1){
            this.appMsg = ext[0];
        }

    }

    /**
     *  异常信息返回， 带返回数据
     * @param data
     * @param stateCode
     * @param ext    1 Result.extInfo ,日常描述，  2.Result.appMsg 用于app文案透出
     */
    public SysAppException(Object data , StateCode stateCode, String... ext) {
        this.stateCode = stateCode;
        this.data = data;
        if (ext.length == 1){
            this.extDescribe = ext[0];
        }else if (ext.length > 1){
            this.appMsg = ext[0];
        }

    }

    /**
     * 系统应用异常构造器
     * @param stateCode
     *                  业务处理结果枚举
     * @param t
     *                  抛出的异常
     */
    public SysAppException(StateCode stateCode, Throwable t) {
        super(t);
        this.stateCode = stateCode;
    }

    /**
     * 扩展描述
     * @param stateCode
     * @param t
     * @param extDescribe
     */
    public SysAppException(StateCode stateCode, Throwable t, String extDescribe) {
        super(t);
        this.stateCode = stateCode;
        this.extDescribe = extDescribe;
    }

    /**
     * 获取业务处理结果枚举
     * @return  业务处理结果枚举
     */
    public StateCode getStateCode() {
        return stateCode;
    }

    /**
     * 获取扩展描述
     * @return property value of extDescribe
     */
    public String getExtDescribe() {
        return extDescribe;
    }

}
