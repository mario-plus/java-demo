package com.mario.mock;

import lombok.Builder;
import lombok.Data;

/**
 * @author zxz
 * @date 2023年03月22日 14:33
 */
@Builder
@Data
public class Device {

    private Long id;

    private String deviceName;

}
