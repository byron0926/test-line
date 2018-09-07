package com.byron.line.constant;

/**
 * @desc: 常量集合
 * @author： byron
 * @createtime： 5/23/20182:25 PM
 * @modify by： ${user}
 * @modify time： 5/23/20182:25 PM
 * @desc of modify：
 * @throws:
 */
public interface Constant {
    /**
     * 密钥对
     */
    interface RSASign {
        String SIGN_KEY = "sign";
        /*私钥*/
        String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKVU0l+5NawNq9LZvJeyY8QHAWwW" +
                "2OEDZBMj9EXWU37g/EB1Gr1XXQ/xKB2ws56sWWPun+xYThK+ykM4uUQh/0+KgiJaoOdn+7d8jv5E" +
                "kWE9e4+Q78W/5uT3nNPlt+ho+E2iZmUVwfuiONcS658ByWtyiKLAf95xSaKxhNCySgkHAgMBAAEC" +
                "gYArpJx0EFwOsv0sh7W1Ba44TPEfK1jM7Sw5sUAGP3GDCLkN+tu4J5u1XZ+NVtvTgwOF0bP9m8Hg" +
                "SOTzocGmLqNKWqekE13JWeMc5n8GmPPw98vgPHHYTKLVToJxMD6Ngq9pHpudyv+eNVzrD9/oCeBD" +
                "gIIfxBdB9olDW10aOTxYgQJBAOX0NQqsDK3zYsd61KdwAWdPyRfP6hu3jDiEYJDgvar1CNeVKmTL" +
                "nv64VzHWNKKgDmRPBFHv8RZwAektt9rStPcCQQC4Ds3WaQkEyJmzL407bZVoemFPuCUPc5CaSFcd" +
                "6lGcu+M3TZVGCwBnrEtdhJO4PTsv4WusMblrBUhmyX1A9xhxAkEA4osdOl9XuoPeG/IZ0L8a0uIa" +
                "lfgChr3kScW4sOKIWQVAacsN0fF8uSt406NZhaGzrATgl6yQUm+UolmlGNKe7wJBALUT+3Yhx/mo" +
                "0W30LmJ1ITS0keA4Ll3ROEZRPUP1L22fe58A+Qb7894LJ+pNcHcl5oDoqWGzWdPsUpqtSEkN0EEC" +
                "QQDNJXmeiqa1n2hQzWTAv6Vjd9DDc6cvMKDl56/8iA42zsbwhEWBEb/RVh7zcLcDKumYa1WtfT3w" +
                "GW1t7F0GFzno";
        /*公钥*/
        String PUBLIC_KDY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClVNJfuTWsDavS2byXsmPEBwFsFtjhA2QTI/RF" +
                "1lN+4PxAdRq9V10P8SgdsLOerFlj7p/sWE4SvspDOLlEIf9PioIiWqDnZ/u3fI7+RJFhPXuPkO/F" +
                "v+bk95zT5bfoaPhNomZlFcH7ojjXEuufAclrcoiiwH/ecUmisYTQskoJBwIDAQAB";
    }
    interface CacheKey {
        /**
         * app版本內存緩存key值
         */
        String APP_FILE_VERSION_CACHE_KEY = "app_key";
        /**
         * 黃牛賬戶金額信息緩存key值
         */
        String SCALPER_ACCOUNT_AMOUNT_CACHE_KEY = "scalper_account_amount_key";
        /**
         * 黄牛支付宝上传固码缓存key值
         */
        String SCALPER_INFO_FIXQR_CACHE_KEY = "scalper_info_fixqr_cache_key";
    }
    interface ApiPrefix {
        /**
         * 接口請求頭令牌參數
         */
        String API_HEADER_TOKEN_NAME = "token";
    }
    interface RedisPrefix {
        /**
         * 黄牛支付宝账号对应昵称前缀
         */
        String SCALPER_ACCOUNT_NICKNAME_PREFIX = "scalper_account_nickname_prefix";
        /**
         * 黃牛驗證碼redis前綴
         * 前綴+：+partnerId+:+黃牛賬號
         */
        String VALIDATION_NUMBER_PREFIX = "validation_number_prefix";
        /**
         * 驗證碼有效時長 單位s 默認5分鐘
         */
        Long VALIDATION_NUMBER_TIMEOUT = 60*5l;
        /**
         * 黃牛搶單redis訂單號緩存前綴
         */
        String TRADE_ORDER_NO_PREFIX = "trade_order_no_prefix";
        /**
         * 黃牛搶單redis訂單號緩存有效時長 單位s 默認5分鐘
         */
        Long TRADE_ORDER_NO_TIMEOUT = 60*5l;
        /**
         * 臨時緩存黃牛搶單訂單號
         * 主要解決：集群環境下，每個節點均會訂閱到超時失效消息，故加次臨時緩存已判斷是否已發送消息
         */
        String TEMPORARY_TRADE_ORDER_NO_PREFIX = "temporary:trade_order_no_prefix";
        /**
         * 臨時緩存黃牛搶單訂單號有效時長  單位s 默認24小時
         */
        Long TEMPORARY_TRADE_ORDER_NO_TIMEOUT = 60*60*24l;
        /**
         * 接口訪問令牌前綴
         */
        String API_TOKEN_PREFIX = "api_token_prefix";
        /**
         * 接口訪問令牌有效時長
         */
        Long API_TOKEN_TIMEOUT = 60*30l;
        /**
         * 黃牛用戶信息緩存前綴
         */
        String SCALPER_INFO_PREFIX = "scalper_info_prefix";
        /**
         * 黃牛用戶信息有效時長
         */
        Long SCALPER_INFO_TIMEOUT = 60*60*24l;
        /**
         * 黃牛今日收益緩存前綴
         */
        String SCALPER_EARNING_PREFIX = "scalper_earning_prefix";
        String SCALPER_EARNING_SUFFIX = "earning";
        String SCALPER_SPEEDING_PREFIX = "scalper_speeding_prefix";
        String SCALPER_SPEEDING_SUFFIX = "speeding";

        interface Sequene {
            /**
             * 序列：邀请码key
             */
            String SEQUENE_ACTIVE_CODE_KEY = "sequene:active_code_key";
        }
    }
    interface Lock {
        String SCALPER_LOCK_PREFIX = "scalper_lock_prefix";
        String COMPANY_LOCK_PREFIX = "company_lock_prefix";
    }
}
