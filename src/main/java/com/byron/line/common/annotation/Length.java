package com.byron.line.common.annotation;

import java.lang.annotation.*;

/**
 * @title : 长度限制
 * @describle :
 * <p>
 *      <b>note:</b>
 *      默认value表示字段固定长度校验 length = value
 *      若max、min非空表示字段长度范围校验 min<= length <= max
 *
 *      注意：该注解主要使用于字符串长度校验
 * </p>
 * Create By byron
 * @date 2018/1/2 10:22 星期二
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Length {
    long min() default 0;
    long max() default Long.MAX_VALUE;
    long value() default 0;
    String message() default "该字段长度限制";
}
