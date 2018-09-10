package com.byron.line.common.util.md5;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SnSdk {

    private static JSONArray bulidDetailContentJosn() {
        JSONArray detailArray = new JSONArray();
        JSONObject detailObject = new JSONObject();
        detailObject.put("serialNo", "1213231231");
        detailObject.put("receiverType", "PERSON");
        detailObject.put("receiverCurrency", "CNY");
        detailObject.put("receiverName", "李岩");
        detailObject.put("amount", 1);
        detailObject.put("orderName", "");
        detailObject.put("bankName", "招商银行");
        detailObject.put("receiverCardNo", "6225880158961301");
        detailObject.put("bankCode", "CMB");
        detailObject.put("bankProvince", "");
        detailObject.put("bankCity", "");
        detailObject.put("payeeBankLinesNo", "");
        detailArray.add(detailObject);
        return detailArray;
    }

    public static String getBatchNo(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static JSONArray buildDetailData(int detailDataNum){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("batchNo", getBatchNo());
        jsonObject.put("merchantNo", "70225705");
        jsonObject.put("productCode", "01070000042");
        jsonObject.put("totalNum", detailDataNum);
        jsonObject.put("totalAmount", 1);
        jsonObject.put("currency", "CNY");
        jsonObject.put("payDate", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        jsonObject.put("tunnelData", "");//可空
        jsonObject.put("goodsType", "");//可空
        jsonObject.put("remark", "");
        jsonObject.put("batchOrderName", "");
        jsonObject.put("detailData", bulidDetailContentJosn());
        jsonObject.put("notifyUrl", "http://47.104.235.121:8095/dora/open/back_notify_snpay");
        jsonArray.add(jsonObject);
        return jsonArray;
    }
}
