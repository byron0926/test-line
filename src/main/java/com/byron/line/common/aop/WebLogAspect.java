package com.byron.line.common.aop;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Aspect
public class WebLogAspect {

    private static final Logger logger = LoggerFactory
            .getLogger(WebLogAspect.class);

    private static ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.dora.tesla.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            startTimeThreadLocal.set(System.currentTimeMillis());
        } catch (Exception e) {
            logger.error("WebLogAspect:doBefore", e);
        }
    }

    @AfterReturning(pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        try {
            long usedTime = System.currentTimeMillis() - startTimeThreadLocal.get();
            Object[] arr = joinPoint.getArgs();
            List<Object> list = Lists.newArrayList();
            for (Object obj : arr) {
                if (obj == null) {
                    continue;
                }
                if (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse) {
                    continue;
                }
                list.add(obj.toString());
            }
            String args = Joiner.on(",").join(list);
            String key = String.format("method=[%s.%s]",
                    joinPoint.getSignature().getDeclaringType().getSimpleName(),
                    joinPoint.getSignature().getName());
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("method=[%s],args=[%s] use time=%d ms", key, args, usedTime));
            } else {
                logger.info(String.format("method=[%s],args=[%s] use time=%d ms", key, args, usedTime));
            }
            logger.debug(String.format("method=[%s],args=[%s] use time=%d ms", key, args, usedTime));
        } catch (Exception e) {
            logger.error("WebLogAspect:doAfterReturning", e);
        }
    }

}
