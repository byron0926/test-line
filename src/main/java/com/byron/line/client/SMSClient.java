package com.byron.line.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.HttpClient;
import com.byron.line.common.util.StringUtils;
import com.byron.line.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.byron.line.common.util.HttpClient.X_WWW_FORM_URL_ENCODED;

/**
 * @desc: 短信业务
 * @author： kidy
 * @createtime： 5/23/20183:33 PM
 * @modify by： ${user}
 * @modify time： 5/23/20183:33 PM
 * @desc of modify：
 * @throws:
 */
public class SMSClient {
    private static final Logger log = LoggerFactory.getLogger(SMSClient.class);
    private static final String SMS_NET_GATEWAY = "https://dx.ipyy.net/smsJson.aspx";
    private static final String PARAMS = "action=send&userid=&account=AB00382&password=AB0038200&mobile={0}&content={1}&sendTime=&extno=";
    private static final String TEMPLATE_SMS = "【喜刷刷】您的注册验证码：{0}，请在{1}分钟之内完成注册！";
    public static boolean sendSMS(String mobile,String content) {
        //https://dx.ipyy.net/sms.aspx?action=send&userid=&account=账号&password=密码&mobile=15023239810,13527576163&content=内容&sendTime=&extno=
        try {
            HttpClient.ResponseResult responseResult =
                    HttpClient.newInstance().sendHttpPost(SMS_NET_GATEWAY,
                                    StringUtils.format(PARAMS,mobile,StringUtils.format(TEMPLATE_SMS,content, Constant.RedisPrefix.VALIDATION_NUMBER_TIMEOUT/60)),
                                    X_WWW_FORM_URL_ENCODED,null);
            if (StringUtils.isEmpty(responseResult)){
                return false;
            }
            JSONObject cont = JSON.parseObject(responseResult.getContent());
            Object status = cont.get("returnstatus");
            Object remainpoint = cont.get("remainpoint");
            if ("Success".equals(status)){
                return true;
            } else if ("0".equals(remainpoint)){
                log.info("money is not enough!!!!!!!!!!!!!!!!");
            }
            responseResult.getContent();
            System.out.println(responseResult);
        } catch (Exception e) {
            log.error("短信业务抛出异常:{}",e.getMessage());
        }
        return false;
    }
}
