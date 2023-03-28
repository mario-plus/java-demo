package com.mario.mock.impl;

import com.mario.abr.down.ICmdDownConvert;
import com.mario.metadata.CmdInfo;
import com.mario.push.PushInfo;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2023年03月23日 14:37
 */
@Service
public class XinSConvertCmd extends ICmdDownConvert {
    @Override
    protected byte[] doCmdDownConvert(PushInfo pushInfo, CmdInfo info) {
        return "物模型key转换".getBytes();
    }


}
