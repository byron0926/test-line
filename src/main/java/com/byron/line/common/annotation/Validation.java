package com.byron.line.common.annotation;

import java.lang.annotation.*;

/**
 * @title : bean校验标记注解标签
 * @describle :
 * <p>
 * Create By byron
 * @date 2017/12/27 14:49 星期三
 */
@Target({
        ElementType.TYPE,
        ElementType.METHOD
        })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Validation {
}
