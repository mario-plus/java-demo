package com.mario.parse;

import com.mario.abr.AbrParseToBytes;
import com.mario.constants.DownMsgType;
import com.mario.parse.down.ParseJsonStrToBytes;
import com.mario.parse.down.ParseStrToBytes;
import com.mario.parse.down.ParseToDirectBytes;
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

    static Map<String, AbrParseToBytes> map = new HashMap<>();

    public synchronized AbrParseToBytes getParse(String msgType) {
        if (map.get(msgType) != null) {
            return map.get(msgType);
        }
        AbrParseToBytes abrParseToBytes;
        if (msgType.equals(DownMsgType.string)) {
            abrParseToBytes = new ParseStrToBytes(reflectUtil);
        } else if (msgType.equals(DownMsgType.json)) {
            abrParseToBytes = new ParseJsonStrToBytes(reflectUtil);
        } else {
            abrParseToBytes = new ParseToDirectBytes(reflectUtil);
        }
        map.put(msgType, abrParseToBytes);
        return abrParseToBytes;
    }
}
