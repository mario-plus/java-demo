package com.mario.parse;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.AbrParseToHex;
import com.mario.metadata.CmdInfo;
import com.mario.metadata.Element;
import com.mario.push.PushInfo;
import com.mario.util.ReflectUtil;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月22日 14:50
 */

public class ParseJsonStrToHex extends AbrParseToHex {

    public ParseJsonStrToHex(ReflectUtil reflectUtil) {
        super(reflectUtil);
    }

    @Override
    protected byte[] doDownConvertByParse(PushInfo pushInfo, CmdInfo cmdInfo) {
        List<Element> elements = setCmdInfoValue(pushInfo, cmdInfo);
        JSONObject jsonObject = new JSONObject();
        elements.forEach(element -> {
            jsonObject.put(element.getKey(), element.getValue());
        });
        return JSON.toJSONString(jsonObject).getBytes();

    }
}
