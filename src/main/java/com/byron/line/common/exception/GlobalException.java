package com.byron.line.common.exception;

import com.byron.line.common.domain.ResponseResult;
import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.util.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalException {
    private static final Logger log = LoggerFactory
            .getLogger(GlobalException.class);

    @ExceptionHandler
    public ResponseResult handle(Exception e) {
        log.error("全局异常捕捉处理");
        log.error(StringUtils.format("异常信息:{0}", e.getMessage()));
        SystemCodeEnum s_ = SystemCodeEnum.SYSTEM_ERROR;
        if (e instanceof UnauthorizedException) {
            return new ResponseResult(-9999, "无权限操作");
        } else if (e instanceof ExpiredSessionException) {
            return new ResponseResult(-9999, "session失效");
        } else if (e instanceof MissingServletRequestParameterException) {
            return new ResponseResult(-9999, "参数丢失");
        } else if (e instanceof IllegalOptaionException) {
            IllegalOptaionException i_ = (IllegalOptaionException) e;
            if (StringUtils.isNotEmpty(i_) && StringUtils.isNotEmpty(i_.getS_())) {
                s_ = i_.getS_();
            }
            log.error("异常信息", e);
            return ResponseResult.builder()
                    .code(-9999)
                    .msg(s_.getDesc())
                    .build();
        } else {
            log.error("异常信息", e);
            return new ResponseResult(-9999, "服务器内部错误", e.getMessage());
        }
    }
}
