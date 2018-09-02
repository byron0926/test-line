package com.byron.line.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 今日收益排名實體類
 * @author： kidy
 * @createtime： 5/28/20185:02 PM
 * @modify by： ${user}
 * @modify time： 5/28/20185:02 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopDto implements Serializable {
    private static final long serialVersionUID = 4825184917597535475L;

    private String account;
    private double score;
}
