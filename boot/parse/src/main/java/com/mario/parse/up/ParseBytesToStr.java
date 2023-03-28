package com.mario.parse.up;

import com.mario.abr.AbrParseBytesToData;
import com.mario.metadata.Element;
import com.mario.metadata.UpLinkMapping;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月27日 20:31
 * bytes 转string
 */
public class ParseBytesToStr extends AbrParseBytesToData<List<Element>> {


    @Override
    protected List<Element> convertBytesToData(byte[] bytes, UpLinkMapping upLinkMapping) {
        String content = new String(bytes, StandardCharsets.UTF_8);

        return null;
    }
}
