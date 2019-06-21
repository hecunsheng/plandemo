package run.star.plan.qr;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务注解
 *      业务编码、
 *      业务名称、
 *      业务主键： 用于定位唯一业务调用
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface SysAppAnnotation {
    /** 是否使用事务 */
    boolean tran() default false;

    /** 是否使用锁 */
    boolean lock() default false;

    /** 加锁后需要解锁 */
    boolean needUnLock()    default true;

    /** 是否使用锁 key  prdexprdex[1.userid][2.storeid]    [] 内为参数取值， 1 代表第一个参数， userid 参数下的属性字段*/
    String lockKey() default "";


    /** 业务主键，用于区分本次请求， prdexprdex[1.userid][2.storeid]    [] 内为参数取值， 1 代表第一个参数， userid 参数下的属性字段*/
    String bizKey() default "";
}
