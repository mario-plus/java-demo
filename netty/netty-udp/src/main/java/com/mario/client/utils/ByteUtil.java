package com.mario.client.utils;

/**
 * @author zxz
 * @date 2023年03月07日 17:47
 */
public class ByteUtil {

    /**
     * 8位16进制(高字节在前)字符串，转10进制字符串IP
     */
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
     * 16进制转10进制整形
     *
     * @param type 3：高字节在前     其他：低字节在前
     */
    protected static String formatValueToDEC(String value, Integer type) {
        if (value.length() == 2 || type == 3) {//1字节
            return Integer.valueOf(value, 16).toString();
        } else {
            //高低位转换
            return Integer.valueOf(exchangeBytes(value), 16).toString();
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
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < value.length() / 2; i++) {
            String substring = value.substring(2 * i, 2 * (i + 1));
            content.insert(0, substring);
        }
        return content.toString();
    }

    /**
     * 浮点型转字节（低八位）
     */
    public static String floatTo4BytesLow(float value) {
        String s = Integer.toHexString(Float.floatToIntBits(Float.parseFloat(String.valueOf(value))));
        if ("0".equals(s)) {
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
        return sb.toString().replace(" ", "");
    }


    /**
     * 16进制转字节数组
     */
    public static byte[] Hex2Bytes(String hexString) {
        byte[] arrB = hexString.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        String strTmp = null;
        for (int i = 0; i < iLen; i += 2) {
            strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }


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
     * 10进制转16进制，高位补0
     */
    public static String decToHex(Integer num, Integer length) {
        return String.format("%0" + length + "x", (long) num);
    }

    /**
     * 10进制转16进制，低位补0
     */
    public static String decToHexLow(Integer num, Integer length) {
        String format = String.format("%0" + length + "x", (long) num);
        return exchangeBytes(format);
    }


    public static String hexBytesToAscii(byte[] bytes) {
        return hexStrToAscii(new String(bytes));
    }

    public static String hexStrToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(hexStrToAscii("2F61636B3A642C302C302C3338342C3139323B0D0A"));
    }

}
