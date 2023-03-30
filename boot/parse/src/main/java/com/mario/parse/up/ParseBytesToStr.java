package com.mario.parse.up;

import com.mario.abr.AbrParseBytesToElements;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpInfo;
import com.mario.metadata.up.UpLinkMapping;
import com.mario.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月27日 20:31
 * bytes 转string
 */
@Slf4j
public class ParseBytesToStr extends AbrParseBytesToElements {


    public ParseBytesToStr(ReflectUtil reflectUtil) {
        super(reflectUtil);
    }

    @Override
    protected List<Element> convertBytesToData(byte[] bytes, UpInfo upInfo) {
        setElementValue(bytes, upInfo);
        return upInfo.getBody();
    }


    @Override
    public String getCmdValue(byte[] bytes, UpLinkMapping upLinkMapping) {
        try {
            Element cmdValue = upLinkMapping.getCmdKey();
            String content = new String(bytes, StandardCharsets.UTF_8);
            return content.substring(cmdValue.getStartIndex(), cmdValue.getLength());
        } catch (Exception e) {
            log.error("无法获取物模型数据key");
            e.printStackTrace();
            return null;
        }
    }

    public void setElementValue(byte[] bytes, UpInfo upInfo) {
        String content = new String(bytes, StandardCharsets.UTF_8);
        List<Element> elements = upInfo.getBody();
        elements.forEach(element -> setElement(content, element));
    }

    public void setElement(String content, Element element) {
        Integer startIndex = element.getStartIndex();
        Integer length = element.getLength();
        String data = content.substring(startIndex, length);
        if (element.getElements() != null && !element.getElements().isEmpty()) {
            element.getElements().forEach(child -> setElement(content, child));
        } else {
            element.setValue(data);
        }
        //setElementTargetValue(element, data);
    }

}
