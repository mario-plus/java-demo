package com.mario.parse.up;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.AbrParseBytesToElements;
import com.mario.constants.ElementTargetType;
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
 * 功能：将上行数据（bytes to jsonStr），转成需要的格式(target jsonStr)，
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
            if (element.getTargetType() != null && element.getTargetType().equals(ElementTargetType.dynamicArray)) {
                JSONArray parse = JSONArray.parse(resource.get(element.getKey()).toString());
                JSONArray data = new JSONArray();
                parse.forEach(e -> {
                    //List<Element> childElements = new ArrayList<>(element.getElements());//浅拷贝
                    List<Element> childElements = JSONArray.parseArray(JSONArray.toJSONString(element.getElements()), Element.class);//深拷贝
                    childElements.forEach(child -> {
                        setElementValue((JSONObject) e, child);
                    });
                    String jsonStr = elementsToJsonStr(childElements, new JSONObject());
                    data.add(JSONObject.parse(jsonStr));
                });
                element.setValue(data);
            } else {
                JSONObject jsonObject = (JSONObject) resource.get(element.getKey());
                element.getElements().forEach(child -> setElementValue(jsonObject, child));
            }
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
