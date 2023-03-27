package com.mario.parse;

import com.mario.abr.AbrParseToHex;
import com.mario.constants.DownMsgType;
import com.mario.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxz
 * @date 2023年03月23日 16:01
 */
@Component
public class ParseFactory {

    @Autowired
    ReflectUtil reflectUtil;

    static Map<String, AbrParseToHex> map = new HashMap<>();

    public synchronized AbrParseToHex getParse(String msgType) {
        if (map.get(msgType) != null) {
            return map.get(msgType);
        }
        AbrParseToHex abrParseToHex;
        if (msgType.equals(DownMsgType.string)) {
            abrParseToHex = new ParseStrToHex(reflectUtil);
        } else if (msgType.equals(DownMsgType.json)) {
            abrParseToHex = new ParseJsonStrToHex(reflectUtil);
        } else {
            abrParseToHex = new ParseToDirectHex(reflectUtil);
        }
        map.put(msgType, abrParseToHex);
        return abrParseToHex;
    }
}
