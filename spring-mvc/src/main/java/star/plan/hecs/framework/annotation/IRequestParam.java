package star.plan.hecs.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: hecs
 * @Date: 2018/11/21 10:49
 * @Description:
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IRequestParam {
    String value() default "";
    boolean required() default true;
}
