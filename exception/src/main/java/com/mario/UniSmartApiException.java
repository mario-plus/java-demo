package com.mario;

/**
 * @author zxz
 * @date 2023年02月20日 17:00
 */
public class UniSmartApiException extends ApiException {

    public UniSmartApiException(Long errorCode, String message, Object data, Throwable e) {
        super(errorCode, message, data, e);
    }

    public UniSmartApiException(Long errorCode, Object data) {
        this(errorCode, null, data, null);
    }

    public UniSmartApiException(Long errorCode) {
        this(errorCode, null, null, null);
    }
}
