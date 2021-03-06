package com.byron.line.common.util;

import java.util.UUID;

/**
 * @title : 序号工具类
 * @describle :
 * <p>
 * Create By byron
 * @date 2017/8/31 14:37 星期四
 */
public final class SequenceUitls {
    /**字符集*/
    private final static String CHARACTERS = "1234567890";
    /**随机字符串默认长度*/
    private final static int DEFAULT_LENGTH = 4;

    /**
     * 生成UUID
     *
     * @return
     */
    public final static String getUUID(){
        return UUID.randomUUID().toString().replaceAll(StringUtils.RAIL,StringUtils.NULL);
    }

    /**
     * 生成随机字符串
     *
     * @description: 默认长度4位
     * @return
     */
    public final static String getRandomString(){
        return getRandomString(0);
    }

    /**
     * 生成随机字符串
     *
     * @description: 默认长度4位，输入0长度也默认4位
     * @param length 指定生成随机字符串长度
     * @return
     */
    public final static String getRandomString(int length){
        char [] chars = CHARACTERS.toCharArray();
        if (length<=0){
            length = DEFAULT_LENGTH;
        }
        int base = String.valueOf(CHARACTERS.length()).length();
        char [] result = new char[length];
        for (int i = 0; i < length; i++) {
            Double index =  Math.random()*Math.pow(10,base);
            result[i] = chars[index.intValue()%CHARACTERS.length()];
        }
        return new String(result);
    }
}
