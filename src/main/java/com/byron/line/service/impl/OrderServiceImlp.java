package com.byron.line.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.*;
import com.byron.line.common.util.md5.Digest;
import com.byron.line.component.rocketmq.base.BaseMessage;
import com.byron.line.constant.Constant;
import com.byron.line.domain.Company;
import com.byron.line.domain.OrderDto;
import com.byron.line.mapper.OrderMapper;
import com.byron.line.mapper.TaskFlowMapper;
import com.byron.line.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import static com.byron.line.common.util.md5.Digest.digest;

@Service
public class OrderServiceImlp extends BaseMessage implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImlp.class);

    private static final String HTTP_DOMAIN = "http://47.104.235.121:8092";
    /*抢单接口*/
    private static final String AsyResUrl = HTTP_DOMAIN + "";
    /*取消订单接口*/
    private static final String CANCEL_ORDER_HTTP_URL = HTTP_DOMAIN + "";

//    @Autowired
//    private TransactionMQProducer transactionMQProducer;
//
//    @Autowired
//    private RocketMQProperties properties;

    @Autowired
    private TaskFlowMapper taskFlowMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    @Transactional
    public ResponseResult insertOrderAndReturnEntity(OrderDto orderDto) {
        /*初始化订单状态*/
        switch (orderDto.getChannelCode()) {
            case 1:
                orderDto.setQrcodeUrl("home/root/apliay" + new Random().nextInt(10) + ".jpg");
            case 2:
                orderDto.setQrcodeUrl("home/root/wechat" + new Random().nextInt(10) + ".jpg");
            case 3:
                orderDto.setQrcodeUrl("银行卡号");
        }
        String randomCode = this.checkRepeat();
        System.out.println("随机字符串=" + randomCode);
        /*查询该字符串是否重复*/
        if (orderMapper.queryOrderByRandomCode(randomCode) >= 1) {
            randomCode = RandomStringUtils.randomAlphanumeric(5);
        }
        orderDto.setRandomCode(randomCode);
        orderDto.setOrderNo(orderDto.getCompanyId() + "_" + DateUtil.getFormatDate(new Date(), DateUtils.YYYYMMDDHHMMSS));
        orderDto.setStatus(1);
        int orderId = orderMapper.insertOrder(orderDto);
        if (1 == orderId) {
            logger.info("回查订单orderId={}", orderDto.getId());
            OrderDto synOrderDto = orderMapper.queryOrderById(orderDto.getId());
            return ResponseResult.builder().code(200).msg("入单成功").object(synOrderDto).build();
        } else {
            return ResponseResult.builder().code(-9999).msg("入单失败").build();
        }
    }

    @Override
    public ResponseResult updateOrder(OrderDto orderDto) {
        /*通知异步插入app请求参数*/
        if(1<= taskFlowMapper.queryByOrderNo(orderDto.getOrderNo())){
            return null;
        }else {
            taskFlowMapper.insertTaskFlow(orderDto);
        }
        //----MQ处理异步插入app请求参数-----
//        String json = JSONObject.toJSONString(orderDto);
//        if (!sendMessage(json,json.getBytes(),transactionMQProducer,properties.getProducerProperties().getTopic(),properties.getProducerProperties().getTag2())){
//            throw new IllegalOptaionException(SystemCodeEnum.SEND_MESSAGE_ERROR);
//        }
        /*修改订单状态*/
        if (StringUtils.isEmpty(orderDto.getOrderNo())) {
            return ResponseResult.builder().code(-9999).msg("订单号为空").build();
        }
        if (StringUtils.isEmpty(orderDto.getAmount())) {
            return ResponseResult.builder().code(-9999).msg("订单金额为空").build();
        }
        if (orderMapper.queryOrderByRandomCode(orderDto.getNote()) == 0) {
            return ResponseResult.builder().code(-9999).msg("随机码为空或输入错误").build();
        }
        orderDto.setStatus(2);
        int result = orderMapper.updateOrder(orderDto);
        ResponseResult rd = null;
        if (1 == result) {
            /*异步通知上分*/
            rd = ResponseResult.builder().code(200).msg("关单成功！").build();
            //todo :异步通知商户上分
//            try {
//                HttpClient.ResponseResult re = HttpClient.newInstance().sendHttpPost(orderDto.getNotifyUrl(), JSONObject.toJSONString(orderDto), null, null);
//                if (StringUtils.isEmpty(re)){
//                    throw new IllegalOptaionException(SystemCodeEnum.HTTP_NO_RESPONSE);
//                }
//                if (re.getHttpCode()==200){
//                    orderMapper.notify(orderDto.getOrderNo());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } else {
            rd = ResponseResult.builder().code(-9999).msg("随机码不存在或输入错误").build();
        }
        return rd;
        //todo :商户账变
//        String lock = redisDao.getKey(Constant.Lock.COMPANY_LOCK_PREFIX, orderDto.getCompanyId()+"");
//        boolean locked_company = true;
//        try {
//            if (redisDistributedLock.lock(lock)){
//                companyMapper.updCompany(orderDto);
//                logger.info("开始修改商户可用余额");
//            }else {
//                locked_company = false;
//                logger.info("该商户正在被锁,请等待");
//                throw new IllegalOptaionException(SystemCodeEnum.LOCK_TIMEOUT);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            /*釋放鎖*/
//            logger.info("釋放分布式锁");
//            redisDistributedLock.unlock(lock,locked_company);
//        }
//        return ResponseResult.builder().code(200).msg("该订单自动关单成功").build();
    }

    @Override
    public ResponseResult setOverTimeStatus(Long id) {
        orderMapper.setOverTimeStatus(id);
        return ResponseResult.builder().code(200).build();
    }

    private String checkRepeat() {
//        UUID.randomUUID().timestamp()
//        String randomCode = RandomStringUtils.randomAlphanumeric(5)+DateUtil.getFormatDate(new Date(),"YYYYMMddHHmmss");
        String randomCode = UUID.randomUUID().toString().replace("-","");
        logger.info("uuid={}",UUID.randomUUID().toString());;
        int result = orderMapper.queryOrderByRandomCode(randomCode);
        if (result >= 1) {
            return checkRepeat();
        } else {
            return randomCode;
        }
    }

    public static void main(String[] args) {
        /**
         * 商户充值入单请求参数模型 demo
         */
        /*OrderDto orderDto = OrderDto.builder()
                .notifyUrl("https://demo.html")
                .channelCode(1)
                .companyId(12l)
                .companyOrderNo("123456")
                .playerId("player12")
                .playerName("玩家1")
                .terminal(1)
                .build();
            String reqParam = JSONObject.toJSONString(orderDto);
            System.out.println("JSON字符串=" + reqParam);*/
        /*
        * 商户配置请求参数模型 demo
        *
        * */
     /*   Company company = Company.builder()
                .companyName("AY")
                .email("12345@qq.com")
                .privateKey("qwertyuiop12345")
                .rate(new BigDecimal(0.05))
                .build();
*/

//        logger.info("UUID={}",UUID.randomUUID());
//        String uuid = UUID.fromString(DateUtil.getFormatDate(new Date(),"YYYYMMddHHmmss")).toString();
//        logger.info("UUID={}",uuid);

        String randomCode = UUID.randomUUID().toString().replace("-","");
        logger.info("randomCode={}",randomCode);

        logger.info("uuid={}",UUID.randomUUID().toString());
        OrderDto orderDtoApp = OrderDto.builder()
                .amount(new BigDecimal(0.01).setScale(2,BigDecimal.ROUND_HALF_UP))
                .orderCrtTime("20180910161200")
                .companyId(12l)
                .playerAccount("兴琼187******89")
                .orderNo("20180910200040011100080064729360")
                .playerName("test1")
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
        map.put("companyId",String.valueOf(orderDtoApp.getCompanyId()));
        map.put("playerName",orderDtoApp.getPlayerName());
        map.put("orderNo",orderDtoApp.getOrderNo());
        map.put("playerAccount",orderDtoApp.getPlayerAccount().trim());
        map.put("orderCrtTime",orderDtoApp.getOrderCrtTime());
        map.put("type",String.valueOf(orderDtoApp.getType()));

        String sign = null;
        PrivateKey pk = null;
        try {
            pk = RSAUtil.getPrivateKey(Constant.RSASign.PRIVATE_KEY);
            List<String> list = new ArrayList<>();
            logger.info("map类型参数：{}", map);
            String tesmp = Digest.mapToString(Digest.treeMap(map, list));
            logger.info("MD5之前的字符串={}", tesmp);
            String MD5 = digest(tesmp);
//            String MD5 = "amount"
            logger.info("MD5={}", MD5);
            sign = RSAUtil.sign(MD5, pk);
            logger.info("签名字符串={}", sign);
            logger.info("加签参数：{}", map);
            boolean flag = RSAUtil.vertiy(MD5, sign, RSAUtil.getPublicKey(Constant.RSASign.PUBLIC_KEY));
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
        /*请求参数带上sign进行传参即可*/


    }


}
