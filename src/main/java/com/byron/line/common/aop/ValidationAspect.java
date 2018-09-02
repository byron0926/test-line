package com.byron.line.common.aop;

import com.byron.line.common.annotation.*;
import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.exception.IllegalOptaionException;
import com.byron.line.common.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title : 自定义bean校验
 * @describle :
 * <p>
 *      <b>note:</b>
 *      前提：校验bean必须继承validBase类
 *      使用方式：
 *              1. 配置扫描切面
 *              2. 指定校验方法上添加@Validation
 * </p>
 * Create By byron
 * @date 2017/9/11 16:33 星期一
 */
@Aspect
@Component
@Order(1)
public class ValidationAspect {
    private static final Logger log = LoggerFactory
            .getLogger(ValidationAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 方式一:
     * 设置包路径颗粒扫描切面
     * 粒度比方式二大，方式二只需要再需要验证的方法上添加注解即可
     *
     * 注意：默认控制层包路径必须包含controller或者control包名即可扫描到
     */
    @Pointcut("execution(public * *..*.controller..*.*(..))||" +
            "execution(public * *..*.control..*.*(..)) || " +
            "execution(public * *.control..*.*(..)) || " +
            "execution(public * *.controller..*.*(..)) || " +
            "execution(public * *controller..*.*(..)) || " +
            "execution(public * *control..*.*(..))")
    public void form() {
    }

    /**
     * 方式二：
     * 设置方法颗粒注解扫描切面
     * eg：使用时可在controller层方法上添加@Validation
     */
    @Pointcut("@annotation(com.byron.line.common.annotation.Validation)")
    public void form2() {
    }

    /**
     * request 请求form过滤验证
     * @param joinPoint
     */
    @Before("form2()||form()")
    public void doBefore(JoinPoint joinPoint) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        startTime.set(System.currentTimeMillis());
        /*获取方法中一些信息*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] objs = joinPoint.getArgs();
        for (Object obj:objs) {
            Class cls = obj.getClass();
            /*是否存在验证标签，判断是否需要验证*/
            if (null==cls.getAnnotation(Validation.class)){
                continue;
            }
            /*验证子类所有属性*/
            Field[] fields = cls.getDeclaredFields();
            validation(fields,obj,cls);
            /*验证父类所有public属性*/
            Field[] parentFields = cls.getFields();
            validation(parentFields,obj,cls);
        }
        log.info(StringUtils.format("此请求验证耗时（毫秒） :{0}", (System.currentTimeMillis() - startTime.get())));
    }

    /**
     * 校验属性字段
     * @param fields
     * @param obj
     * @param cls
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void validation(Field[] fields, Object obj, Class cls) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (Field f: fields) {
            Annotation[] ans = f.getAnnotations();
            if (null==ans || ans.length==0){
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(),cls);
            Method method = pd.getReadMethod();
            Object paramVal = method.invoke(obj);
            for (Annotation an : ans) {
                SystemCodeEnum sce = SystemCodeEnum.SYSTEM_ERROR;
                if (an instanceof NotNull){
                    if (StringUtils.isEmpty(paramVal)||"".equals(paramVal)){
                        String message = ((NotNull) an).message();
                        sce.setDesc(StringUtils.isNotEmpty(message)?message:(f.getName()+"参数不能为空"));
                        throw new IllegalOptaionException(sce);
                    }
                } else if (an instanceof Parttern){
                    Parttern p = (Parttern) an;
                    /*或者p.value()*/
                    String partternVal = null==p.regexp()?p.value():p.regexp();
                    Pattern pattern = Pattern.compile(partternVal);
                    Matcher matcher = pattern.matcher(null!=paramVal?paramVal.toString():"");
                    if (!matcher.matches()){
                        String message = ((Parttern) an).message();
                        sce.setDesc(StringUtils.isNotEmpty(message)?message:(f.getName()+"参数与正则表达式【"+partternVal+"】不匹配"));
                        throw new IllegalOptaionException(sce);
                    }
                } else if (an instanceof NotZero){
                    if (null!=paramVal &&!"".equals(paramVal) && "0".equals(String.valueOf(paramVal))){
                        String message = ((NotZero) an).message();
                        sce.setDesc(StringUtils.isNotEmpty(message)?message:(f.getName()+"参数不能为0"));
                        throw new IllegalOptaionException(sce);
                    }
                } else if (an instanceof Length){
                    if (null==paramVal||"".equals(paramVal)){
                        sce.setDesc("参数不能为空");
                        throw new IllegalOptaionException(sce);
                    } else {
                        Length length = (Length) an;
                        if(0!=length.value()){
                            /* = value */
                            if (paramVal.toString().length() != length.value()){
                                sce.setDesc("参数长度必须等于"+length.value());
                                throw new IllegalOptaionException(sce);
                            }
                        }else if (0!=length.min()&&length.max()== Long.MAX_VALUE){
                            /* min ~ + */
                            if (length.min()>paramVal.toString().length()){
                                sce.setDesc("参数最小长度是"+length.min());
                                throw new IllegalOptaionException(sce);
                            }
                        } else if (0==length.min()&&length.max()!= Long.MAX_VALUE){
                            /* 0 ~ max */
                            if (paramVal.toString().length()>length.max()){
                                sce.setDesc("参数最大长度是"+length.max());
                                throw new IllegalOptaionException(sce);
                            }
                        } else {
                            /* min ~ max */
                            if (length.min()>paramVal.toString().length()||
                                    paramVal.toString().length()>length.max()){
                                sce.setDesc(String.format("参数长度必须是%d~%d范围内",length.min(),length.max()));
                                throw new IllegalOptaionException(sce);
                            }
                        }
                    }
                }
            }
        }
    }
}
