package com.mario.parse.up;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.AbrParseBytesToElements;
import com.mario.constants.Constants;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpInfo;
import com.mario.metadata.up.UpLinkMapping;
import com.mario.util.ByteUtil;
import com.mario.util.ReflectUtil;
import com.mario.util.StringUtil;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
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
        String content = ByteUtil.byte2Hex(bytes);
        setElementValue(upInfo.getBody(), content);
        return upInfo.getBody();
    }

    private void setElementValue(List<Element> elements, String content) {
        elements.forEach(element -> {
            String elementContent = content.substring(element.getStartIndex(), (element.getStartIndex() + element.getLength()));
            if (element.getTargetType() != null && element.getTargetType().equals(Constants.dynamicArray)) {//动态数组，element.getElements()不能为空
//                JSONArray data = new JSONArray();
//                for (int i = 0; i < elementContent.length() / element.getArrayLength() * 2; i++) {
//                    List<Element> childElements = JSONArray.parseArray(JSONArray.toJSONString(element.getElements()), Element.class);//深拷贝
//                    String arrayE = elementContent.substring(element.getLength() * 2 * i, element.getLength() * 2 * (i + 1));//单个元素内容
//                    setElementValue(childElements, arrayE);
//                    String jsonStr = elementsToJsonStr(childElements, new JSONObject());
//                    data.add(JSONObject.parse(jsonStr));
//                }
//                element.setValue(data);
            } else {//直接赋值
                if (!CollectionUtils.isEmpty(element.getElements())) {//子节点，用二进制str进行解析
                    String binLFromDec = ByteUtil.getBinLFromDec(Integer.parseInt(elementContent, 16), elementContent.length() * 4);
                    setElementValue(element.getElements(), binLFromDec);
                } else {
                    element.setValue(elementContent);
                }
            }
            setElementTargetValue(element, elementContent);
        });
    }

    @Override
    public String getCmdValue(byte[] bytes, UpLinkMapping upLinkMapping) {//提供简单的物模型key解析，复杂数据可通过convert进行获取
        String content = ByteUtil.byte2Hex(bytes);
        Element cmdKey = upLinkMapping.getCmdKey();
        return content.substring(cmdKey.getStartIndex(), cmdKey.getLength());
    }
}
