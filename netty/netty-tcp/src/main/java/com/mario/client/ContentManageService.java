package com.mario.client;
import java.util.*;

/**
 * @author zxz
 * @date 2023年03月09日 14:57
 */
public class ContentManageService {


    private static String getContent(String functionKey, String type, LinkedHashMap<String, Object> data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/").append(functionKey).append(":").append(type);
        data.values().forEach(e -> stringBuilder.append(",").append(e.toString()));
        return stringBuilder.append(";").toString();
    }

    private static String getDecContent(String functionKey, String type, LinkedHashMap<String, Object> data) {
        return getContent(functionKey, "d", data);
    }

    private static String getHexContent(String functionKey, String type, LinkedHashMap<String, Object> data) {
        return getContent(functionKey, "h", data);
    }



}
