/**
 *
 */
package com.buterfleoge.whale.exception;

/**
 * @author xiezhenzong
 *
 */
public class WeixinException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5858954211414363381L;

    /**
     *
     */
    public WeixinException() {
    }

    /**
     * @param message
     */
    public WeixinException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public WeixinException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public WeixinException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public WeixinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
