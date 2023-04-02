package com.mario.abr;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mario.constants.ElementTargetType;
import com.mario.metadata.Element;
import com.mario.util.ByteUtil;
import com.mario.util.StringUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月28日 15:45
 */
public interface Parse {

    //该方法类型可根据用户需求扩展
    default void setElementTargetValue(Element element, Object valueFromD) {
        switch (element.getTargetType()) {
            case ElementTargetType.stringType:
                element.setValue(valueFromD);
                break;
            case ElementTargetType.array:
                element.setValue(JSONArray.from(valueFromD));
                break;
            case ElementTargetType.jsonObj:
                element.setValue(JSONObject.parseObject(valueFromD.toString()));
                break;
            case ElementTargetType.highHex:
                element.setValue(ByteUtil.getHexFromDec(Integer.parseInt(String.valueOf(valueFromD)), element.getLength()));
                break;
            case ElementTargetType.lowHex:
                element.setValue(ByteUtil.getLowHexFromDec(Integer.parseInt(String.valueOf(valueFromD)), element.getLength()));
                break;
            case ElementTargetType.intType:
                element.setValue(Integer.parseInt(String.valueOf(valueFromD)));
                break;
            case ElementTargetType.arraySize:
                element.setValue(JSONArray.from(valueFromD).size());
                break;
            case ElementTargetType.arraySizeToHex:
                element.setValue(ByteUtil.getHexFromDec(JSONArray.from(valueFromD).size(), element.getLength()));
                break;
            case ElementTargetType.booleanType:
                element.setValue(!"0".equals(valueFromD.toString()));
                break;
            case ElementTargetType.hexToInt:
                element.setValue(ByteUtil.getDecFromHex(valueFromD.toString()));
                break;
            case ElementTargetType.lowHexToInt:
                element.setValue(ByteUtil.getDexFromLowHex(valueFromD.toString()));
                break;
            default:
                break;
        }
    }


    default String elementsToJsonStr(List<Element> elements, JSONObject jsonObject) {
        elements.forEach(element -> {
            if (CollectionUtils.isEmpty(element.getElements())) {
                jsonObject.put(element.getKey(), element.getValue());
            } else {//有子节点
                if (element.getTargetType().equals(ElementTargetType.dynamicArray)) {//动态数组数据（解析时就已经将数据解析出放在value中）
                    jsonObject.put(element.getKey(), element.getValue());
                } else {
                    if (StringUtil.isEmpty(element.getKey())) {//无key，子节点数据自动升级
                        elementsToJsonStr(element.getElements(), jsonObject);
                    } else {
                        JSONObject child = new JSONObject();
                        jsonObject.put(element.getKey(), child);
                        elementsToJsonStr(element.getElements(), child);
                    }
                }
            }
        });
        return JSON.toJSONString(jsonObject);
    }
}
