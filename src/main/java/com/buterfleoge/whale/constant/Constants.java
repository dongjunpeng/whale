package com.buterfleoge.whale.constant;

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

}
