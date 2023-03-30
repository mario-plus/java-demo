package com.mario.parse.up;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.AbrParseBytesToElements;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpInfo;
import com.mario.metadata.up.UpLinkMapping;
import com.mario.util.ReflectUtil;
import com.mario.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月28日 10:18
 */
@Slf4j
public class ParseBytesToJsonStr extends AbrParseBytesToElements {

    public ParseBytesToJsonStr(ReflectUtil reflectUtil) {
        super(reflectUtil);
    }

    @Override
    protected List<Element> convertBytesToData(byte[] bytes, UpInfo upInfo) {
        //解析数据
        JSONObject jsonObject = JSON.parseObject(new String(bytes, StandardCharsets.UTF_8));
        upInfo.getBody().forEach(element -> setElementValue(jsonObject, element));
        return upInfo.getBody();
    }

    public void setElementValue(JSONObject resource, Element element) {
        if (element.getElements() != null && !element.getElements().isEmpty()) {
            JSONObject jsonObject = (JSONObject) resource.get(element.getKey());
            element.getElements().forEach(child -> setElementValue(jsonObject, child));
        } else {
            element.setValue(StringUtil.getValueFromD(StringUtil.getValueFromS1(element.getValue().toString()), resource));
        }
    }


    @Override
    public String getCmdValue(byte[] bytes, UpLinkMapping upLinkMapping) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(new String(bytes, StandardCharsets.UTF_8));
            Element cmdValue = upLinkMapping.getCmdKey();
            String key = cmdValue.getKey();
            return jsonObject.get(StringUtil.getValueFromS1(key)).toString();
        } catch (Exception e) {
            log.error("无法获取物模型数据key");
            e.printStackTrace();
            return null;
        }
    }
}
