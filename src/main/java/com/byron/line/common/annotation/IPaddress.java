package com.byron.line.common.annotation;

import java.lang.annotation.*;

/**
 * @desc: 记录IP解析注解，通过添加此注解记录请求IP信息
 * @author： byron
 * @createtime： 4/20/20189:24 AM
 * @modify by： ${user}
 * @modify time： 4/20/20189:24 AM
 * @desc of modify：
 * @throws:
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IPaddress {
}
