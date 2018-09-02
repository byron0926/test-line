package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: app文件實體類
 * @author： kidy
 * @createtime： 5/26/20181:00 PM
 * @modify by： ${user}
 * @modify time： 5/26/20181:00 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppFile implements Serializable {
    private static final long serialVersionUID = 4480737053137235289L;

    private long id;
    private String version;
    private String name;
    private String url;
    private int status;
    private int isForce;
    private int osType;
}
