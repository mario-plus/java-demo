package com.mario.mock.impl;

import com.mario.abr.up.ICmdKeyUpConvert;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年04月02日 10:18
 */

@Component
public class XinSCmdKeyConvert extends ICmdKeyUpConvert {
    @Override
    public String doCmdKeyUpConvert(byte[] bytes) {
        return "k1";
    }
}
