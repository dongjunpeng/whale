package com.buterfleoge.whale;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * 常量
 *
 * @author xiezhenzong
 *
 */
public interface Constants {

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

        /**
         * 邮箱已经存在
         */
        int EMAIL_EXIST = 1;

        /**
         * 密码错误
         */
        int WRONG_PASSWORD = 2;

        /**
         * 邮箱不存在
         */
        int EMAIL_NO_EXIST = 3;

        /**
         * 错误账户类型
         */
        int WRONG_ACCOUNT_TYPE = 4;

        /**
         * 密码不合法
         */
        int INVALID_PASSWORD = 5;

        /**
         * 验证码错误
         */
        int INVALID_VALID_CODE = 6;
    }

    /**
     * 错误信息
     *
     * @author xiezhenzong
     *
     */
    public interface ErrorMsg {
        String EMAIL_EXIST = "邮箱已经存在";

        String WRONG_PASSWORD = "密码错误";

        String EMAIL_NO_EXIST = "邮箱不存在";

        String WRONG_ACCOUNT_TYPE = "错误账户类型";

        String INVALID_PASSWORD = "密码不合法";

        String INVALID_VALID_CODE = "验证码错误";
    }

    /**
     * 格式
     *
     * @author xiezhenzong
     *
     */
    public interface Pattern {

        String DATE_TIME = "yyyy-MM-dd mm:ss";

    }

    /**
     * session key
     *
     * @author xiezhenzong
     *
     */
    public interface SessionKey {

        String ACCOUNT_BASIC_INFO = "account_basic_info";

        String WX_LOGIN_STATE = "wx_state";

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
     * 
     *
     * @author xiezhenzong
     *
     */
    public interface CacheKey {

        String WX_ACCESS_TOKEN_PREFIX = "rabbit_wx_access_token";

    }

    /**
     * default value
     *
     * @author xiezhenzong
     *
     */
    public interface DefaultValue {

        long VALID_MAIL_INVALID_PERIOD = 60 * 1000;

        String TOKEN = "dkdkdkdkdkdkdk";

        String SEPARATOR = "_";

        String EMAIL_FROM = "zhenzong.peng@icloud.com";

        String WX_SCOPE_SEPARATOR = ",";

        int COOKIE_EXPIRY = 7 * 24 * 60 * 60;
    }

    /**
     * prefix
     *
     * @author xiezhenzong
     *
     */
    public interface Prefix {
        String VALID_MAIL_CACHE_PREFIX = "valid_mail_";
    }

    /**
     * key
     *
     * @author xiezhenzong
     *
     */
    public interface Key {

        String VALID_MAIL_VALID_CODE = "valid_code";

    }

}
