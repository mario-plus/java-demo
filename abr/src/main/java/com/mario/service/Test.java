package com.mario.service;

import com.mario.msg.AbrParams;
import com.mario.msg.uniLight.UniLightParam;
import com.mario.service.impl.uniLight.XsLightServiceImpl;

/**
 * @author zxz
 * @date 2023年02月28日 20:00
 */
public class Test {
    public static void main(String[] args) throws Exception {
        XsLightServiceImpl xsLightService = new XsLightServiceImpl();
        UniLightParam uniLightParam = new UniLightParam();
        uniLightParam.setDeviceId(1L);
        doProcess(xsLightService,uniLightParam);

    }

    public static void doProcess(AbrInteract abrInteract, AbrParams params) throws Exception {
       abrInteract.doProcess(params);
    }
}
