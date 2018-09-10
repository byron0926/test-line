package com.byron.line.constant;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.RSAUtil;
import com.byron.line.common.util.md5.Digest;
import com.byron.line.domain.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.byron.line.common.util.md5.Digest.digest;

public class TestDemo {

    private static Logger logger = LoggerFactory.getLogger(TestDemo.class);
    public static  void main(String[] args){
    OrderDto orderDtoApp = OrderDto.builder()
            .amount(new BigDecimal(100))
            .playerAccount("12345@qq.com")
            .orderCrtTime("2018-05-12 10:12:24")
            .orderNo("12_20180908151411")
            .note("QWERt")
            .type(1)
            .build();
    String orderDtoAppStr = JSONObject.toJSONString(orderDtoApp);
        System.out.println("app请求访问参数=" + orderDtoAppStr);

    /*
     *  RSA加密签名解密demo
     *  提示：参与签名实体字段值需要转换为String类型 然后调用api按照字母和数字顺序 排序
     * */
    Map<String,String> map = new HashMap<String,String>();
        map.put("amount",String.valueOf(orderDtoApp.getAmount()));
        map.put("note",orderDtoApp.getNote());
        map.put("orderNo",orderDtoApp.getOrderNo());
        map.put("playerAccount",orderDtoApp.getPlayerAccount());
        map.put("orderCrtTime",orderDtoApp.getOrderCrtTime());
        map.put("type",String.valueOf(orderDtoApp.getType()));

    String sign = null;
    PrivateKey pk = null;
        try {
        pk = RSAUtil.getPrivateKey(Constant.RSASign.PRIVATE_KEY);
        List<String> list = new ArrayList<>();
        logger.info("map类型参数：{}", map);
        String tesmp = Digest.mapToString(Digest.treeMap(map, list));
        logger.info("参与签名字符串格式={}", tesmp);
        String MD5 = digest(tesmp);
        logger.info("MD5={}", MD5);
        sign = RSAUtil.sign(tesmp, pk);
        logger.info("签名字符串={}", sign);
        logger.info("加签参数：{}", map);
        boolean flag = RSAUtil.vertiy(MD5,sign, RSAUtil.getPublicKey(Constant.RSASign.PUBLIC_KEY));
        logger.info("本地验签结果={}", flag);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (InvalidKeySpecException e) {
        e.printStackTrace();
    } catch (InvalidKeyException e) {
        e.printStackTrace();
    } catch (SignatureException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("sign", sign);
//        map.put("signAlgorithm", "RSA");

    }
}
