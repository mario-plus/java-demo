package com.mario.abr.down;
import com.mario.metadata.DownLinkMapping;
import com.mario.push.PushInfo;

/**
 * @author zxz
 * @date 2023年03月23日 10:06
 * 所有服务convert都需要实现这个抽象类,
 */
public abstract class IServiceDownConvert extends IDownConvert<byte[], PushInfo, DownLinkMapping> {

    @Override
    protected byte[] doDownConvert(PushInfo info, DownLinkMapping downLinkMapping) {
        return doServiceDownConvert(info,downLinkMapping);
    }

    protected abstract byte[] doServiceDownConvert(PushInfo pushInfo, DownLinkMapping downLinkMapping);
}
