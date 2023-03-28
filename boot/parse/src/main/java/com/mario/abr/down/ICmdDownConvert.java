package com.mario.abr.down;

import com.mario.abr.down.IDownConvert;
import com.mario.metadata.CmdInfo;
import com.mario.push.PushInfo;

/**
 * @author zxz
 * @date 2023年03月23日 10:11
 * 单个物模型转换
 */
public abstract class ICmdDownConvert extends IDownConvert<byte[], PushInfo, CmdInfo> {
    @Override
    protected byte[] doDownConvert(PushInfo pushInfo, CmdInfo cmdInfo) {
        return doCmdDownConvert(pushInfo, cmdInfo);
    }

    protected abstract byte[] doCmdDownConvert(PushInfo o, CmdInfo info);


}
