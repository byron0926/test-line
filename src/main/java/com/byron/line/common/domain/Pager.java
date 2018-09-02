package com.byron.line.common.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: 分頁響應實體類
 * @author： byron
 * @createtime： 5/20/20186:32 PM
 * @modify by： ${user}
 * @modify time： 5/20/20186:32 PM
 * @desc of modify：
 * @throws:
 */
@Data
@Builder
public class Pager implements Serializable {
    /*总数*/
    private long total;
    /*页面总数*/
    private int pages;
    /*数据*/
    private List list;
    /*扩展字段*/
    private Object obj;
}
