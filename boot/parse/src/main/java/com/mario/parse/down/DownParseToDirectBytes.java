package com.mario.parse.down;

import com.mario.abr.AbrDownParseToBytes;
import com.mario.metadata.down.CmdInfo;
import com.mario.metadata.Element;
import com.mario.push.PushInfo;
import com.mario.util.ByteUtil;
import com.mario.util.ReflectUtil;
import com.mario.util.StringUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zxz
 * @date 2023年03月22日 14:51
 */
public class DownParseToDirectBytes extends AbrDownParseToBytes {

    public DownParseToDirectBytes(ReflectUtil reflectUtil) {
        super(reflectUtil);
    }

    @Override
    protected byte[] doDownConvertByParse(PushInfo pushInfo, CmdInfo cmdInfo) {
        List<Element> elements = setCmdInfoValue(pushInfo, cmdInfo);
        List<Element> byteGroup = elements.stream().filter(element -> element.getByteGroup() != null).collect(Collectors.toList());
        elements.removeAll(byteGroup);
        Map<Integer, List<Element>> collect = byteGroup.stream().collect(Collectors.groupingBy(Element::getByteGroup));
        collect.forEach((k, v) -> elements.add(getElementFromByte(v)));
        List<String> hex = elements.stream().sorted(Comparator.comparing(Element::getOrder))
                .map(element -> element.getValue().toString()).collect(Collectors.toList());
        return ByteUtil.Hex2Bytes(StringUtil.getStrFromList(hex));
    }


    public Element getElementFromByte(List<Element> elements) {
        if (elements.stream().mapToInt(Element::getLength).sum() % 8 != 0) {
            return null;
        }
        elements.forEach(e -> e.setValue(ByteUtil.getBinLFromDec(Integer.parseInt(e.getValue().toString()), e.getLength())));
        List<String> values = elements.stream().sorted(Comparator.comparing(Element::getOrder))
                .map(element -> element.getValue().toString()).collect(Collectors.toList());
        String strFromList = StringUtil.getStrFromList(values);
        Element element = new Element();
        element.setOrder(elements.get(0).getOrder());
        element.setValue(Integer.toHexString(Integer.parseInt(strFromList, 2)));
        return element;
    }
}
