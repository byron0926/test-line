package com.byron.line.domain;

import com.byron.line.common.annotation.NotNull;
import com.byron.line.common.annotation.ValidBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "ScalperLoginReq请求参数对象")
@Data
public class User extends ValidBase implements Serializable {

    private static final long serialVersionUID = 4953071662151924317L;
    @ApiModelProperty(value = "userName 黃牛賬號",name = "account")
    @NotNull(message = "账户不能为空")
    private String userName;
    @ApiModelProperty(value = "Password 密碼",name = "password")
    @NotNull(message = "密碼不能爲空")
    private String password;
}
