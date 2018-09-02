package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 合作商
 * @author： kidy
 * @createtime： 5/23/20182:48 PM
 * @modify by： ${user}
 * @modify time： 5/23/20182:48 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Partner implements Serializable {
    private static final long serialVersionUID = -8369871137119105038L;
    private long id;
    private String name;
}
