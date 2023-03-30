package com.mario.abr.up;

import com.mario.metadata.Element;
import com.mario.metadata.up.UpLinkMapping;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月28日 11:41
 */
public abstract class ICmdUpConvert extends IUpConvert<List<Element>, byte[], UpLinkMapping> {
    @Override
    public List<Element> doUpConvert(byte[] bytes, UpLinkMapping upLinkMapping) {
        return doCmdUpConvert(bytes, upLinkMapping);
    }

    protected abstract List<Element> doCmdUpConvert(byte[] bytes, UpLinkMapping upLinkMapping);
}
