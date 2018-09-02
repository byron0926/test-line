package com.byron.line.common.enums;


import com.byron.line.common.util.StringUtils;

/**
 * @title : 查询时间类型枚举
 * @describle :
 * <p>
 * Create By byron
 * @date 2017/7/13 11:08 星期四
 */
public enum DateTypeEnum {
    TODAY(0, "今日"),
    YESTERDAY(1,"昨日"),
    THIS_MONTH(2,"本月"),
    LAST_MONTH(3,"上月");

    private int code;
    private String desc;

    private DateTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public final static DateTypeEnum newInstance(int code) {
        DateTypeEnum[] s_ = DateTypeEnum.values();
        if (StringUtils.isNotEmpty(s_)) {
            for (DateTypeEnum s_s : s_) {
                if (s_s.getCode() == code) {
                    return s_s;
                }
            }
        }
        return null;
    }
}
