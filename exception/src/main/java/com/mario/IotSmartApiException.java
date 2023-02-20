package com.mario;

/**
 * @author zxz
 * @date 2023年02月20日 17:02
 */
public class IotSmartApiException extends ApiException {

    public IotSmartApiException(Long errorCode, String message, Object data, Throwable e) {
        super(errorCode, message, data, e);
    }

    public IotSmartApiException(Long errorCode, String message, Object data) {
        super(errorCode, message, data, null);
    }


    public IotSmartApiException(Long errorCode) {
        super(errorCode, null, null, null);

    }

}
