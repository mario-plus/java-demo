package com.mario.msg.uniLight;

import com.mario.msg.AbrParams;
import lombok.Data;

import java.util.List;

/**
 * @author zxz
 * @date 2023年02月28日 17:34
 */
@Data
public class UniLightParam extends AbrParams {
    /**
     * 设备id
     */
    private Long deviceId;


    /**
     * 设备集合，批量操作
     */
    private List<Long> idList;

    /**
     * 物模型key，用于定位处理方法
     */
    private String modelKey;


    /**
     * 参数
     * */
    private String params;


}
