package sun.star.plan.qr;

import org.slf4j.Logger;

/**
 * @Author: hecs
 * @Date: 2018/10/10 14:20
 * @Description:
 */
public interface LoggerEnumInterface {
    /**
     * 日志枚举接口
     *
     * <p>
     *      主要用于约束通过枚举，获取代码和备注，可以使实现本接口的枚举形成统一
     * </p>
     *
     * 返回日志对象
     * @return
     * @date: 2015年11月19日 下午3:05:15
     */
    public Logger getLogger();

    /**
     *
     * @return 枚举代码
     */
    public String getCode();

    /**
     * 接口描述，用于业务日志输出
     * @return 枚举代码描述
     */
    public String getMessage();
}
