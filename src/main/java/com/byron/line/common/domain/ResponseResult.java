package com.byron.line.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseResult(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

}
