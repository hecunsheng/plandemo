package run.star.plan.log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author hecs
 * @date 2020-07-13 11:15
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface LogCompar {
    /**
     * 中文名称
     *
     * @return String
     */
    String name();

    /**
     * Date 如何格式化，默认为空
     *
     * @return String
     */
    String dateFormat() default "";
}
