package com.byron.line.controller;

import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.exception.IllegalOptaionException;
import com.byron.line.common.util.RSACoderUtils;
import com.byron.line.common.util.StringUtils;
import com.byron.line.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import java.util.Map;

/**
 * @desc: 控制層基類
 * @author： kidy
 * @createtime： 3/19/20183:10 PM
 * @modify by： ${user}
 * @modify time： 3/19/20183:10 PM
 * @desc of modify：
 * @throws:
 */
public class BaseController {
    public static Logger log = LoggerFactory
            .getLogger(BaseController.class);

    /**
     * 异常处理
     *
     * @param e
     * @throws IllegalOptaionException
     */
    public void exception(Exception e,String requestMethod) throws IllegalOptaionException {
        log.error(StringUtils.format("請求方法[{0}]拋出異常信息:{1}",requestMethod,e.getMessage()));
        if (e instanceof IllegalOptaionException) {
            throw new IllegalOptaionException(((IllegalOptaionException) e).getS_());
        }
        if (e instanceof DuplicateKeyException){
            throw new IllegalOptaionException(SystemCodeEnum.DUPLICATE_ERROR);
        }
        log.error("系统异常信息{}",e);
        throw new IllegalOptaionException(SystemCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 验证签名
     * @param sign
     * @param data
     */
    public void signVerify(Object sign,Map<String,Object> data) throws Exception {
        if (StringUtils.isEmpty(sign)){
            throw new IllegalOptaionException(SystemCodeEnum.ILLEGAL_REQUEST);
        }
        if(!RSACoderUtils.verify(RSACoderUtils.formatParameter(data), Constant.RSASign.PUBLIC_KEY,sign+StringUtils.NULL)){
            throw new IllegalOptaionException(SystemCodeEnum.SIGN_ERROR);
        }
    }
}
