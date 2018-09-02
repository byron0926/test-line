package com.byron.line.res;


import com.byron.line.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestRes {

    private Long id;
    private String name;
    private Integer status;
    private String statusName;

    public String getStatusName() {
        if(StringUtils.isEmpty(status)){
            return "状态为空";
        }
        if(1==status){
            return "有效状态";
        }else {
            return "无效状态";
        }

    }
}
