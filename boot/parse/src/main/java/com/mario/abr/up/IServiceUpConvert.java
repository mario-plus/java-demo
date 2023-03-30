package com.mario.abr.up;

import com.mario.abr.down.IDownConvert;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpLinkMapping;


import java.util.List;

/**
 * @author zxz
 * @date 2023年03月28日 10:39
 */
public abstract class IServiceUpConvert extends IUpConvert<List<Element>, byte[], UpLinkMapping> {


    @Override
    public List<Element> doUpConvert(byte[] bytes, UpLinkMapping upLinkMapping) {
        return doServiceUpConvert(bytes, upLinkMapping);
    }

    protected abstract List<Element> doServiceUpConvert(byte[] bytes, UpLinkMapping upLinkMapping);

}
