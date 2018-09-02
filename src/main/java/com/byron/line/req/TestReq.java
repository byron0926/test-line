package com.byron.line.req;

import com.byron.line.common.annotation.NotNull;
import com.byron.line.common.annotation.ValidBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "addMen請求參數")
@Data
public class TestReq extends ValidBase implements Serializable {

    private static final long serialVersionUID = 556939713705171173L;
    @ApiModelProperty(value = "id",name = "id")
    private Long id;
    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "真實姓名",name = "name")
    private String name;
    @ApiModelProperty(value = "莊戶狀態",name = "status")
    private Integer status;
}
