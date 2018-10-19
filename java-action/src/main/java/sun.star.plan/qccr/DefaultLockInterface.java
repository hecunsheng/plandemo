package sun.star.plan.qccr;

/**
 * @Author: hecs
 * @Date: 2018/10/10 14:21
 * @Description:
 */
public interface DefaultLockInterface {
    /**
     * 加锁
     * @param lockKey
     * @return
     * @date: 2015年11月23日 下午5:35:38
     */
    SysAppResult lock(String lockKey);

    /**
     * 解锁
     * @param lockKey
     * @return
     * @date: 2015年11月23日 下午5:35:47
     */
    SysAppResult unlock(String lockKey);
}
