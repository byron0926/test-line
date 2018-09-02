package com.byron.line.dto;

import com.byron.line.domain.Fixqr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 固码配置实体
 * @author： kidy
 * @createtime： 6/6/20184:42 PM
 * @modify by： ${user}
 * @modify time： 6/6/20184:42 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixqrConfigDto implements Serializable {
    private static final long serialVersionUID = 8071361458591307291L;
    private int id;
    private int parentId;
    private BigDecimal amount;
    private Fixqr fixqr;
}
