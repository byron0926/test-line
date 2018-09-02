package com.byron.line.dto;

import com.byron.line.domain.ScalperAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 登錄返回實體
 * @author： kidy
 * @createtime： 5/24/20186:47 PM
 * @modify by： ${user}
 * @modify time： 5/24/20186:47 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResultDto implements Serializable {
    private static final long serialVersionUID = 1907098820092463561L;

    private ScalperAccount scalperAccount;
    private String token;
}
