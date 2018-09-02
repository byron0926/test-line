package com.byron.line.common.annotation;

import java.lang.annotation.*;

/**
 * @title : 正则验证
 * @describle :
 * <p>
 * Create By byron
 * @date 2017/12/27 15:18 星期三
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Parttern {
    String regexp();
    String value() default "";
    String message() default "该字段值与正则表达式不匹配";
}
