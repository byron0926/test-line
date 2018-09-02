package com.byron.line.filter;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.ctx.SpringApplicationContext;
import com.byron.line.common.domain.ResponseResult;
import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.util.StringUtils;
import com.byron.line.component.redis.api.RedisDao;
import com.byron.line.component.redis.api.impl.RedisDaoImpl;
import com.byron.line.constant.Constant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describle: token校驗
 * @Author: byron
 * @Date: Created By 下午 2:14 2017/5/29 0029
 * @Modified By
 */
public class TokenValidationFilter implements Filter {
    public static Logger log = Logger.getLogger(TokenValidationFilter.class);
    private FilterConfig config;
    private String excludePage;
    private String tag;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        excludePage = config.getInitParameter("excludePage");
        tag = config.getInitParameter("tag");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!"Y".equalsIgnoreCase(tag)) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] strs = excludePage.split(StringUtils.SEMICOLON);
        //过滤页面
        log.info(StringUtils.format("请求地址：{0}", request.getRequestURI()));
        log.info(StringUtils.format("是否过滤:{0}", isContains(request.getRequestURI(), strs)));
        if (isContains(request.getRequestURI(), strs)) {
            ResponseResult op = null;
            /** 读取请求头数据 */
            String urlToken = request.getHeader(Constant.ApiPrefix.API_HEADER_TOKEN_NAME);
            /**校验请求URL是否带有token*/
            if (StringUtils.isEmpty(urlToken)){
                op = ResponseResult.builder()
                        .code(SystemCodeEnum.ILLEGAL_REQUEST.getCode())
                        .msg(SystemCodeEnum.ILLEGAL_REQUEST.getDesc())
                        .build();
            } else {
                try {
                    RedisDao redisDao  = (RedisDaoImpl) SpringApplicationContext.getBean("redisDaoImpl");
                    /**校验token是否有效*/
                    String tempToken = redisDao.get(redisDao.getKey(Constant.RedisPrefix.API_TOKEN_PREFIX,urlToken));
                    if (StringUtils.isEmpty(tempToken)){
                        op = ResponseResult.builder()
                                .code(SystemCodeEnum.ILLEGAL_TOKEN.getCode())
                                .msg(SystemCodeEnum.ILLEGAL_TOKEN.getDesc())
                                .build();
                    } else {
                        /**token有效，更新token有效時間*/
                        redisDao.expire(redisDao.getKey(Constant.RedisPrefix.API_TOKEN_PREFIX,urlToken),Constant.RedisPrefix.API_TOKEN_TIMEOUT);
                    }
                } catch (Exception e) {
                    log.info("過濾token拋出異常信息：{}",e);
                }

            }
            if (StringUtils.isNotEmpty(op)){
                response.getWriter().write(JSONObject.toJSONString(op));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        config = null;
    }

    private boolean isContains(String container, String[] strs) {
        if (container.equals("/")){
            return false;
        }
        for (String url : strs) {
            if (container.indexOf(url) != -1) {
                return false;
            }
        }
        return true;
    }
}
