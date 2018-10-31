package sun.star.plan.qccr;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 */
public class SysAppMethod {

    /** 用于输出日志的入参 */
    private Object[]             obj;

    /** 摘要日志标记信息   不允许包含 ， */
    private String               digestTag     = null;

    /**  是否使用事务  */
    private boolean              transaction    = false;

    /**  是否加锁  ，启用加锁后，需要实现lockKey返回*/
    private boolean              isLock        = false;

    /**  是否进行幂等校验， 启用幂等校验后，默认启动加锁  */
    private boolean              isIdempotence = false;

    /** 需要解锁 */
    private boolean              needUnLock    = false;

    /** 锁key */
    private String               lockKey       ;

    /**  类名 */
    private String               className ;

    /** 方法名 */
    private String               methodName;

    /**  系统名 */
    private String               sysName;

    @Override
    public  String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public SysAppMethod() {
        super();
    }

    public ActionEnumInterface getActionEnum() {
        return new Actions(className+"."+ methodName,  className, sysName, null);
    }

    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        this.obj = obj;
    }

    public String getDigestTag() {
        return digestTag;
    }

    public void setDigestTag(String digestTag) {
        this.digestTag = digestTag;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isIdempotence() {
        return isIdempotence;
    }

    public void setIdempotence(boolean idempotence) {
        isIdempotence = idempotence;
    }

    public boolean isNeedUnLock() {
        return needUnLock;
    }

    public void setNeedUnLock(boolean needUnLock) {
        this.needUnLock = needUnLock;
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public boolean isTransaction() {
        return transaction;
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
    }
}
