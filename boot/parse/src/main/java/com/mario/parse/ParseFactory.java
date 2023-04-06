package com.mario.parse;

import com.mario.abr.AbrParseBytesToElements;
import com.mario.abr.AbrDownParseToBytes;
import com.mario.constants.Constants;

import com.mario.parse.down.DownParseJsonStrToBytes;
import com.mario.parse.down.DownParseStrToBytes;
import com.mario.parse.down.DownParseToDirectBytes;
import com.mario.parse.up.ParseBytesDirect;
import com.mario.parse.up.ParseBytesToJsonStr;
import com.mario.parse.up.ParseBytesToStr;
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

    static Map<String, AbrDownParseToBytes> map = new HashMap<>();

    static Map<String, AbrParseBytesToElements> upMap = new HashMap<>();

    public synchronized AbrDownParseToBytes getParse(String msgType) {
        if (map.get(msgType) != null) {
            return map.get(msgType);
        }
        AbrDownParseToBytes abrParseToBytes;
        if (msgType.equals(Constants.string)) {
            abrParseToBytes = new DownParseStrToBytes(reflectUtil);
        } else if (msgType.equals(Constants.json)) {
            abrParseToBytes = new DownParseJsonStrToBytes(reflectUtil);
        } else {
            abrParseToBytes = new DownParseToDirectBytes(reflectUtil);
        }
        map.put(msgType, abrParseToBytes);
        return abrParseToBytes;
    }

    public synchronized AbrParseBytesToElements getUpParse(String msgType) {
        if (upMap.get(msgType) != null) {
            return upMap.get(msgType);
        }
        AbrParseBytesToElements upParse;
        if (msgType.equals(Constants.string)) {
            upParse = new ParseBytesToStr(reflectUtil);
        } else if (msgType.equals(Constants.json)) {
            upParse = new ParseBytesToJsonStr(reflectUtil);
        } else {
            upParse = new ParseBytesDirect(reflectUtil);
        }
        upMap.put(msgType, upParse);
        return upParse;
    }
}
