package com.byron.line.common.aop;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.annotation.VerifySign;
import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.exception.IllegalOptaionException;
import com.byron.line.common.util.RSACoderUtils;
import com.byron.line.common.util.StringUtils;
import com.byron.line.constant.Constant;
import com.byron.line.domain.OrderDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @title : 签名校验
 * @describle :
 * <p>
 *      <b>note:</b>
 *      仅适用@Requestbody单个请求入参实体的方法，入参中不准有list 对象等类信息
 *      使用方式：
 *              1. 配置扫描切面
 *              2. 指定校验方法上添加@VerifySign
 *  * </p>
 * Create By byron
 * @date 2017/9/11 16:33 星期一
 */
@Aspect
@Component
@Order(2)
public class VerifySignAspect {
    private static final Logger log = LoggerFactory
            .getLogger(VerifySignAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 方式二：
     * 设置方法颗粒注解扫描切面
     * eg：使用时可在controller层方法上添加@VerifySign
     */
    @Pointcut("@annotation(com.byron.line.common.annotation.VerifySign)")
    public void form2() {
    }

    /**
     * request 请求form过滤验证
     * @param joinPoint
     */
//    @Before("form2()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        String sign = request.getHeader("sign");
//        String body = request.getQueryString();
//        log.info("body={}",body);
//        OrderDto orderDto = (OrderDto) JSONObject.parse(body);
//        String sign = orderDto.getSign();
//        log.info("aop中获取的sign={}",sign);
//        if (StringUtils.isEmpty(sign)){
//            throw new IllegalOptaionException(SystemCodeEnum.ILLEGAL_REQUEST);
//        }
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Annotation[] annotations = signature.getMethod().getAnnotations();
//        OrderDto obj = (OrderDto) joinPoint.getArgs()[0];
//        obj.getSign();



//        Map<String,Object> params = new HashMap<>();
//        if (StringUtils.isNotEmpty(annotations)){
//            for (Annotation annotation : annotations){
//                if (annotation instanceof VerifySign){
//                    VerifySign verifySign = (VerifySign) annotation;
//                    Field[] fields = verifySign.value().getDeclaredFields();
//                    for (Field field : fields) {
//                        if (!field.getName().equals("serialVersionUID")){
//                            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), verifySign.value());
//                            Method method = pd.getReadMethod();
//                            params.put(field.getName(),method.invoke(obj));
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//        Map map = new HashMap();
//        map.put("amount",orderDto)
//        if(!RSACoderUtils.verify(RSACoderUtils.formatParameter(params), Constant.RSASign.PUBLIC_KEY,sign)){
//            throw new IllegalOptaionException(SystemCodeEnum.SIGN_ERROR);
//        }
        log.info(StringUtils.format("此请求验证耗时（毫秒） :{0}", (System.currentTimeMillis() - startTime.get())));
    }
}
