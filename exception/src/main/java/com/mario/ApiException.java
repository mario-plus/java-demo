package com.mario;

/**
 * @author zxz
 * @date 2023年02月20日 16:50
 *
 * 异常链
 * Throwable
 * Exception
 * RuntimeException
 *
 */
public class ApiException extends RuntimeException {

    private Long errorCode;
    private Object data;

    public ApiException(Long errorCode, String message, Object data, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }


    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
