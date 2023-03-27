package com.mario.mock;

import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年03月22日 16:08
 */
@Component
public class DeviceService {

    public String getSerialNum(Long deviceId) {
       return "DN9846783";
    }


}
