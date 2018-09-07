package com.byron.line.domain;

import com.byron.line.common.annotation.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "app注册请求参数对象")
@Data
public class ResgisteReq implements Serializable {
    private static final long serialVersionUID = -5951335694331355209L;
    @NotNull(message = "userName is not null")
    @ApiModelProperty(value = "userName app賬號",name = "account")
    private String userName;
    @NotNull(message = "password is not null")
    @ApiModelProperty(value = "Password 黃牛賬號",name = "password")
    private String password;

}
