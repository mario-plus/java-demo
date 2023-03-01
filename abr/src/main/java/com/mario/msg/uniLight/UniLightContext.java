package com.mario.msg.uniLight;

import com.mario.msg.AbrContext;
import lombok.Data;

/**
 * @author zxz
 * @date 2023年02月28日 17:35
 */
@Data
public class UniLightContext extends AbrContext {

    private UniLightParam param;//参数

    private String context;//下发内容

    private UniLightParam result;//响应结果

}
