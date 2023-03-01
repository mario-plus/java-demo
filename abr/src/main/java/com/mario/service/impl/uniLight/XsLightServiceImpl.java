package com.mario.service.impl.uniLight;

import com.mario.msg.uniLight.UniLightContext;
import com.mario.msg.uniLight.UniLightParam;
import com.mario.msg.uniLight.UniLightResult;
import com.mario.service.UniLightInteract;

/**
 * @author zxz
 * @date 2023年02月28日 19:42
 */

public class XsLightServiceImpl extends UniLightInteract<UniLightParam, UniLightContext, UniLightResult> {



    @Override
    protected void init(UniLightParam param) {
        Long deviceId = param.getDeviceId();
        //校验设备是否在线
        System.out.println("数据校验正常");

        String modelKey = param.getModelKey();

        System.out.println("物模型key不能为null");
    }

    @Override
    protected UniLightContext makeLightOrder(UniLightParam param) {
        System.out.println("指令拼接成功");
        return new UniLightContext();
    }

    @Override
    protected UniLightResult sendLightOrder(UniLightContext context) {
        System.out.println("指令下发成功");
        return new UniLightResult();
    }

    @Override
    protected boolean dealLightRes(UniLightResult result) {
        System.out.println("指令响应处理成功");
        return true;
    }
}
