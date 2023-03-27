package com.mario.push;

import lombok.Data;

/**
 * @author zxz
 * @date 2023年03月23日 9:42
 */
@Data
public class PushInfo {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 物模型key
     */
    private String functionKey;


    /**
     * 下行数据(JsonStr)
     */
    private Object context;


}
