package com.buterfleoge.whale;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * 常量
 *
 * @author xiezhenzong
 *
 */
public interface Constants {

    String LOCAL = Utils.getLocalHost();

    /**
     * 状态码
     *
     * @author xiezhenzong
     *
     */
    public interface Status {

        /**
         * 调用成功
         */
        int OK = 0;

        /**
         * 参数异常
         */
        int PARAM_ERROR = 100;

        /**
         * 对方返回异常，比如，null
         */
        int RESPONSE_ERROR = 200;

        /**
         * 超时异常
         */
        int TIMEOUT_ERROR = 300;

        /**
         * 网络异常，不包含上面的超时异常，比如： reset by peer等
         */
        int NETWORK_ERROR = 400;

        /**
         * 系统异常
         */
        int SYSTEM_ERROR = 500;

        /**
         * 权限异常，自行定义
         */
        int AUTH_ERROR = 600;

        /**
         * 超限异常，比如，超出配额，但是，如果参数过长，请放在参数异常那里，
         */
        int EXCEED_ERROR = 700;

        /**
         * 部分成功
         */
        int PARTLY_OK = 800;

        /**
         * 处理中，不一定是异常，也不一定是正常的，，素以请自行判断在哪儿使用
         */
        int PROCESSING = 900;

        /**
         * 数据库异常
         */
        int DB_ERROR = 1000;

        /**
         * 业务异常
         *
         * @see Response#getStatus()
         */
        int BIZ_ERROR = 1100;

        /**
         * 外部服务异常
         */
        int INVOKE_ERROR = 1200;

        /**
         * 上面发生的各种异常之外的异常
         */
        int UNKNOW_ERROR = 9999999;

    }

    /**
     * 业务异常
     *
     * @author xiezhenzong
     *
     */
    public interface BizCode {

        int GROUP_NOT_EXIST = 1;

        int DISCOUNT_CODE_NOT_EXIST = 2;

        int EXCEED_MAX_ORDER_TRAVELLER_COUNT = 3;

        int GROUP_QUOTA_FULL = 4;
    }

    /**
     * 错误信息
     *
     * @author xiezhenzong
     *
     */
    public interface ErrorMsg {

        String GROUP_NOT_EXIST = "团不存在";
        String DISCOUNT_CODE_NOT_EXIST = "优惠码错误";
        String EXCEED_MAX_ORDER_TRAVELLER_COUNT = "超过报名人数限制";
        String GROUP_QUOTA_FULL = "超过团人数限制";
        String DISCOUNT_CODE_OCCUPIED = "优惠码已使用，请取消订单后重新验证";
        String DISCOUNT_CODE_TIMEOUT = "优惠码已过期";
        String DISCOUNT_CODE_USED = "优惠码已被使用";
        String CREATE_ORDER_VALIDATE_FAILED = "创建订单校验失败";
    }

    /**
     * 格式
     *
     * @author xiezhenzong
     *
     */
    public interface Pattern {
        String DATE = "yyyy-MM-dd";
    }

    /**
     * session key
     *
     * @author xiezhenzong
     *
     */
    public interface SessionKey {

        String ACCOUNT_BASIC_INFO = "account_basic_info";

    }

    /**
     * cookie key
     *
     * @author xiezhenzong
     *
     */
    public interface CookieKey {

        String ACCOUNTID = "accountid";

        String TOKEN = "token";

    }

    /**
     * cache key
     *
     * @author xiezhenzong
     *
     */
    public interface CacheKey {

        String WX_ACCESS_TOKEN_PREFIX = "rabbit_wx_access_token";

        String WX_LOGIN_STATE_PREFIX = "wx_state";

    }

    /**
     * default value
     *
     * @author xiezhenzong
     *
     */
    public interface DefaultValue {

        long VALID_MAIL_INVALID_PERIOD = 60 * 1000;

        String TOKEN = "hxy.travel.token.secret";

        String SEPARATOR = "_";

        String EMAIL_FROM = "zhenzong.peng@icloud.com";

        String WX_SCOPE_SEPARATOR = ",";

        int COOKIE_EXPIRY = 7 * 24 * 60 * 60;

        int MAX_ORDER_TRAVELLER_COUNT = 5;
    }

}
