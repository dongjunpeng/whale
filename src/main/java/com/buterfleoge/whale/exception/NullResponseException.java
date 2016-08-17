package com.buterfleoge.whale.exception;

/**
 * 返回null的response异常
 * 
 * 避免有些方法没有异常抛出声明，所以继承{@code RuntimeException}
 * 
 * @author xiezhenzong
 * 
 */
public class NullResponseException extends RuntimeException {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = -1565605923396004715L;

    /**
     * empty constructor
     */
    public NullResponseException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public NullResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public NullResponseException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NullResponseException(Throwable cause) {
        super(cause);
    }

}
