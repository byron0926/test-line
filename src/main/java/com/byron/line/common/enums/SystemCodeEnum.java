package com.byron.line.common.enums;

import com.byron.line.common.util.StringUtils;

/**
 * @desc: 系统状态码枚举
 *        系統異常編碼：
 *              0-500        系統異常編碼範圍
 *        業務異常編碼：
 *              10000-10099  系統用戶異常編碼範圍
 *
 * @author： byron
 * @createtime： 3/19/20182:57 PM
 * @modify by： ${user}
 * @modify time： 3/19/20182:57 PM
 * @desc of modify：
 * @throws:
 */
public enum SystemCodeEnum {

    SUCCESSFUL(1,"response successfully"),
    NOT_LAST_VERSION(-9999,"已经是最新版本，无需更新"),
    SYSTEM_ERROR(-9999,"系统错误"),
    HTTP_NO_RESPONSE(-9999,"http no response"),
    FAIL(-9999,"转账失败"),
    DUPLICATE_ERROR(-9999,"请不要重复插入数据"),
    VALIDATION_ILLEGAL(-9999,"验证码错误"),
    PARTNER_ILLEGAL(-9999,"非法合作商户ID"),
    SMS_ERROR(-9999,"短信发送失败"),
    ILLEGAL_REQUEST(-9999,"请求不合法"),
    ILLEGAL_TOKEN(-7777,"非法访问令牌token"),
    ILLEGAL_ACTIVE_CODE(-9999,"邀请码不存在"),
    ACTIVE_CODE_MAX_TIMES_ERROR(-9999,"邀请码已超过最大邀请次数"),
    ACTIVE_CODE_MAX_LENGTH_ERROR(-9999,"黄牛总数已超过4位数，请修改数据库邀请码字段长度"),
    OLD_PASSWORD_ERROR(-9999,"旧密码错误"),
    SCALPER_ACCOUNT_NOT_EXIST(-9999,"账号不存在"),
    PARAM_IS_NOT_NULL(-9999,"参数不能为空"),
    SEND_MESSAGE_ERROR(-9999,"推送消息失败"),
    SCALPER_ALIPAY_NOT_BIND(-9999,"该账户还未绑定支付宝账号，请先绑定支付宝"),
    SCALPER_ALIPAY_NOT_ACCESS(-9999,"该账户绑定支付宝账号还在审核中"),
    NO_DATA(-9999,"OCR解析失败"),
    TRADE_ORDER_IS_NOT_EXIST(-9999,"交易订单不存在"),
    TRADE_ORDER_TYPE_IS_NOT_EXIST(-9999,"非法订单交易类型"),
    RE_UPLOAD_IMAGE(-9999,"reupload images"),
    ORDER_NO_IS_NOT_EXIST(-9999,"订单不存在"),
    COMPANY_IS_NOT_EXIST(-9999,"商户不存在"),
    ORDER_NO_IS_NOT_EXIST_OR_STAUTS_ERROR(-9999,"订单不存在或者订单状态已被关闭"),
    SIGN_ERROR(-9999,"签名错误"),
    TYPE_ERROR(-9999,"不支持的渠道类型"),
    AVALIBLE_AMOUNT_IS_NOT_ENOUGH(-9999,"黄牛可用余额不足"),
    LOCK_TIMEOUT(-9999,"lock time out"),
    FSDSFAST_FILE_UPLOAD_TIMEOUT(-9999,"文件服务器上传超时");

    private int code;
    private String desc;

    private SystemCodeEnum(int code, String desc) {
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

    public final static SystemCodeEnum newInstance(int code) {
        SystemCodeEnum[] s_ = SystemCodeEnum.values();
        if (StringUtils.isNotEmpty(s_)) {
            for (SystemCodeEnum s_s : s_) {
                if (s_s.getCode() == code) {
                    return s_s;
                }
            }
        }
        return null;
    }
}
