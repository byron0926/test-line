package com.byron.line.common.ctx;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring application context
 * <p>
 * <p>
 * You can get beans by SpringApplicationContext. and this Bean's name can be the package name
 * or the name of the class. but must to be configurated in your spring-mybatis.xml.
 * </p>
 *
 * @author byron
 * @date 2017/05/22 11:17:26.
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    public static Object getBean(String name) throws ClassNotFoundException {
        try {
            return applicationContext.getBean(name);
        } catch (BeansException e) {
            try {
                Class cls = Class.forName(name);
                return applicationContext.getBean(cls);
            } catch (ClassNotFoundException c) {
                throw new ClassNotFoundException(c.getMessage(), c);
            }
        }
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
