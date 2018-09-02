package com.byron.line.common.annotation;

import java.lang.annotation.*;

/**
 * @title : 非空，即:不等于null 且 不等于""
 * @describle :
 * <p>
 * Create By byron
 * @date 2017/12/27 15:16 星期三
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
    String message() default "该字段不能为空";
}
