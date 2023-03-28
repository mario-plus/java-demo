package com.mario.abr;

import com.mario.abr.up.IUpConvert;
import com.mario.metadata.UpLinkMapping;

/**
 * @author zxz
 * @date 2023年03月27日 20:23
 */
public abstract class AbrParseBytesToData<R> extends IUpConvert<R, byte[], UpLinkMapping> {


    @Override
    protected R doUpConvert(byte[] bytes, UpLinkMapping upLinkMapping) {
         return convertBytesToData(bytes, upLinkMapping);
    }

    protected abstract R convertBytesToData(byte[] bytes, UpLinkMapping upLinkMapping);


}
