package com.mario.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月25日 15:03
 */
public class StringUtil {


    /**
     * 是否是${}数据
     */
    public static boolean s1Data(String value) {
        return value.startsWith("${") && value.endsWith("}");
    }


    /**
     * 是否是#{}数据
     */
    public static boolean j1Data(String value) {
        return value.startsWith("#{") && value.endsWith("}");
    }

    /**
     * 取${},#{}数据
     */
    public static String getValueFromS1(String string) {
        return string.substring(2, string.length() - 1);
    }


    /**
     * 取分割符第一个元素
     *
     * @param c 需要转义
     */
    public static String getFirstSplitByChar(String content, String c) {
        return content.split(c)[0];
    }


    /**
     * json里面获取数据
     * xxx.xxx.xxx.....
     */
    public static Object getValueFromD(String str, JSONObject jsonObject)  {
        if (str.contains(".")) {
            String firstSplitByChar = getFirstSplitByChar(str, "\\.");
            Object o = jsonObject.get(firstSplitByChar);
            if (o != null) {
                JSONObject childRes = JSONObject.parseObject(JSON.toJSONString(o));
                String replace = str.replace(firstSplitByChar + ".", "");
                return getValueFromD(replace, childRes);
            } else {
                return null;
            }
        } else {
            return jsonObject.get(str);
        }
    }


    /**
     * 字符替换
     * 指定起始位置
     */
    public static String replaceChar(String resource, Integer index, String rep) {
        if (resource.length() < index + rep.length()) {
            return resource;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String start = resource.substring(0, index);
        String end = resource.substring(index + rep.length());
        stringBuilder.append(start).append(rep).append(end);
        return stringBuilder.toString();
    }


    /**
     * List<String> to String
     * */
    public static String getStrFromList(List<String> list) {
        return String.join("", list);
    }
}
