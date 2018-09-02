package com.byron.line.common.annotation;

import java.lang.annotation.*;

/**
 * @desc: 校验签名注解
 * @author： byron
 * @createtime： 6/4/201811:23 AM
 * @modify by： ${user}
 * @modify time： 6/4/201811:23 AM
 * @desc of modify：
 * @throws:
 */
@Target({
        ElementType.TYPE,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface VerifySign {
    Class value() default Object.class;
}
