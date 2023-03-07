package com.mario.client.utils;

import com.mario.client.bo.ByteSplit;
import com.mario.client.bo.BytesType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxz
 * @date 2023年03月07日 17:47
 */
public class ByteUtil {

    public static boolean checkSplitRule(List<ByteSplit> byteSplits, String content) {
        if (byteSplits.isEmpty()) {
            return false;
        }
        List<ByteSplit> collect = orderSplitRule(byteSplits);
        ByteSplit byteSplit = collect.get(collect.size() - 1);
        if (content.length() < (byteSplit.getStartIndex() + byteSplit.getLength())) {
            return false;//字符串长度小于切割长度
        }
        if (collect.size() < 2) {
            return true;
        }
        for (int i = 0; i < collect.size() - 1; i++) {
            ByteSplit before = collect.get(i);
            ByteSplit after = collect.get(i + 1);
            if (before.getStartIndex() > after.getStartIndex()
                    || (before.getStartIndex() + before.getLength() > after.getStartIndex())) {
                return false;
            }
        }
        return true;
    }

    protected static List<ByteSplit> orderSplitRule(List<ByteSplit> byteSplits) {
        return byteSplits
                .stream()
                .sorted(Comparator.comparing(ByteSplit::getStartIndex).reversed())
                .collect(Collectors.toList());
    }


    public static List<ByteSplit> doSplitByte(List<ByteSplit> byteSplits, String content) {
        orderSplitRule(byteSplits).forEach(e -> {
            String value = content.substring(e.getStartIndex(), e.getStartIndex() + e.getLength());
            e.setValue(formatValue(value, e.getType()));
            System.out.println("模型数据：" + e);
        });
        return byteSplits;
    }

    /**
     * 格式化数据
     */
    protected static String formatValue(String value, Integer type) {
        if (type.equals(BytesType.TYPE_IP)) {
            return formatValueToIp(value);
        } else if (type.equals(BytesType.TYPE_HEX_TO_DEC)
                || type.equals(BytesType.TYPE_HEX_TO_DEC_HIGH)
                || type.equals(BytesType.TYPE_HEX_TO_DEC_LOW)) {
            return formatValueToDEC(value, type);
        } else if (type.equals(BytesType.TYPE_HEX_TO_FLOAT_LOW)) {
            return formatValueToDecFloat(value);
        }
        return value;
    }


    //8位16进制字符串，转10进制字符串IP
    protected static String formatValueToIp(String value) {
        if (value.length() > 8) {
            return value;
        }
        return HexStrToDecIp(value);
    }

    /**
     * 16进制字符串转IP
     */
    protected static String HexStrToDecIp(String value) {
        if (value.length() % 2 != 0) {
            return value;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < value.length() / 2; i++) {
                if (i != 0) {
                    stringBuilder.append(".");
                }
                stringBuilder.append(Integer.valueOf(value.substring(2 * i, 2 * (i + 1)), 16).toString());
            }
            return stringBuilder.toString();
        }
    }


    /**
     * 1字节，2字节（高八位，低八位），16进制转10进制整形
     */
    protected static String formatValueToDEC(String value, Integer type) {
        if (value.length() == 2 || type.equals(BytesType.TYPE_HEX_TO_DEC_HIGH)) {//1字节
            return Integer.valueOf(value, 16).toString();
        } else {
            String start = value.substring(0, 2);
            String end = value.substring(2, 4);
            return Integer.valueOf(end + start, 16).toString();
        }
    }

    /**
     * 多字节转float（低八位）16进制转10进制小数点
     */
    protected static String formatValueToDecFloat(String value) {
        Long l = Long.parseLong(exchangeBytes(value), 16);
        float v = Float.intBitsToFloat(l.intValue());
        return String.valueOf(v);
    }


    /**
     * 高低位转换
     */
    protected static String exchangeBytes(String value) {
        if (value.length() % 2 != 0) {
            return value;
        }
        String content = "";
        for (int i = 0; i < value.length() / 2; i++) {
            String substring = value.substring(2 * i, 2 * (i + 1));
            content = substring + content;
        }
        return content;
    }

    /**
     * 浮点型转字节（低八位）
     */
    public static String floatTo4BytesLow(float value) {
        String s = Integer.toHexString(Float.floatToIntBits(Float.parseFloat(String.valueOf(value))));
        if ("0".equals(s)){
            return "00000000";
        }
        return exchangeBytes(s);
    }


    /**
     * 字节转16进制字符串
     */
    public static String byte2HexWithBlank(byte[] b) {
        if ((b == null) || (b.length == 0)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(b.length * 3);
        int size = b.length;
        for (int n = 0; n < size; n++) {
            sb.append("0123456789ABCDEF".charAt(0xF & b[n] >> 4)).append("0123456789ABCDEF".charAt(b[n] & 0xF)).append(" ");
        }
        return sb.toString();
    }


    /**
     * 十进制转16进制低字节
     */
    public static String decToByteLow(int num) {
        String value = Integer.toHexString(num);
        if (value.length() % 2 != 0) {
            value = "0" + value;
        }
        return exchangeBytes(value);
    }


    public static void main(String[] args) {
        String s = floatTo4BytesLow((float) 0.0);
        System.out.println(s);
    }


}
