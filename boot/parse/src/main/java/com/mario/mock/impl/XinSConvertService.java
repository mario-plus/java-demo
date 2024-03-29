package com.mario.mock.impl;

import com.mario.abr.down.IServiceDownConvert;
import com.mario.metadata.down.DownLinkMapping;
import com.mario.push.PushInfo;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2023年03月23日 14:00
 */
@Service
public class XinSConvertService extends IServiceDownConvert {

    @Override
    protected byte[] doServiceDownConvert(PushInfo pushInfo, DownLinkMapping downLinkMapping) {
        return "我是xins转换器".getBytes();
    }

}
