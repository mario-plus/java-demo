package com.mario.parse.down;

import com.mario.abr.AbrParseToBytes;
import com.mario.metadata.CmdInfo;
import com.mario.metadata.Element;
import com.mario.push.PushInfo;
import com.mario.util.ReflectUtil;

import java.util.*;


/**
 * @author zxz
 * @date 2023年03月22日 14:46
 * 将数据解析成str,再转成byte[]
 */
public class ParseStrToBytes extends AbrParseToBytes {


    public ParseStrToBytes(ReflectUtil reflectUtil) {
        super(reflectUtil);
    }

    @Override
    protected byte[] doDownConvertByParse(PushInfo pushInfo, CmdInfo cmdInfo) {
        List<Element> elements = setCmdInfoValue(pushInfo, cmdInfo);
        StringBuilder stringBuilder = new StringBuilder();
        elements.stream().sorted(Comparator.comparing(Element::getOrder))
                .forEach(element -> stringBuilder.append(element.getValue().toString()));
        return stringBuilder.toString().getBytes();
    }
}
