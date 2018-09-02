package com.byron.line.common.config;

import com.byron.line.filter.TokenValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @title : 过滤器配置管理
 * @describle :
 * <p>
 * Create By byron
 * @date 2017/9/11 9:45 星期一
 */
@Configuration
public class FilterConfiguration {

    @Bean
    @Order(Integer.MAX_VALUE-1)
    public FilterRegistrationBean filterDemo4Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new TokenValidationFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        /*排除过滤页面配置*/
        registration.addInitParameter("excludePage","/byron/line");
        /*是否启用过滤标记Y-启用 N-不启用*/
        registration.addInitParameter("tag","Y");
        //过滤器名称
        registration.setName("TokenValidationFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(false);
        return registration;
    }
}
