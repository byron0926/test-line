package com.byron.line.client;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.HttpClient;
import com.byron.line.common.util.StringUtils;
import com.byron.line.dto.OCRInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;

/**
 * @desc: 支付截图OCR解析客户端
 * @author： kidy
 * @createtime： 5/29/20186:13 PM
 * @modify by： ${user}
 * @modify time： 5/29/20186:13 PM
 * @desc of modify：
 * @throws:
 */
public class OCRClient {
    private static final Logger log = LoggerFactory.getLogger(OCRClient.class);
    /*映射内网IP*/
    private static final String OCR_HTTP_URL = "http://172.31.232.128:8585/ocr/ocrAPI/upload";
    /*映射外网IP*/
//    private static final String OCR_HTTP_URL = "http://47.104.148.2:8585/ocr/ocrAPI/upload";

    public static OCRInfoDto analysisOCR(File file) {
        try {
            HttpClient.ResponseResult re = HttpClient.newInstance().sendHttpPostFile(OCR_HTTP_URL,
                    file);
            if (StringUtils.isEmpty(re)){
                return null;
            }
            JSONObject obj = JSONObject.parseObject(re.getContent());
            obj = obj.getJSONObject("orderinfo");
            return OCRInfoDto.builder()
                    .amount(StringUtils.isEmpty(obj.getString("amount"))?null:new BigDecimal(obj.getString("amount")))
                    .type(obj.getInteger("type"))
                    .txType(obj.getString("txType"))
                    .receiver(obj.getString("receiver"))
                    .receiveAccount(obj.getString("receiveAccount"))
                    .orderNum(obj.getString("orderNum"))
                    .orderDate(obj.getString("orderDate"))
                    .status(obj.getInteger("status"))
                    .build();
        } catch (Exception e) {
            log.error("OCR解析异常:{}",e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(analysisOCR(new File("E:\\456.jpg")));
    }
}
