package com.mario.parse.up;

import com.mario.abr.AbrParseBytesToElements;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpInfo;
import com.mario.metadata.up.UpLinkMapping;
import com.mario.util.ReflectUtil;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月28日 10:01
 */
public class ParseBytesDirect extends AbrParseBytesToElements {

    public ParseBytesDirect(ReflectUtil reflectUtil) {
        super(reflectUtil);
    }

    @Override
    protected List<Element> convertBytesToData(byte[] bytes, UpInfo upInfo) {
        return null;
    }

    @Override
    public String getCmdValue(byte[] bytes, UpLinkMapping upLinkMapping) {
        return null;
    }
}
