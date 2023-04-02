package com.mario.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月07日 17:47
 */
public class ByteUtil {


    /**
     * 字节高低位转换
     */
    public static String exchangeBytes(String value) {
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
     * 字节转16进制字符串
     */
    public static String byte2Hex(byte[] b) {
        if ((b == null) || (b.length == 0)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(b.length * 3);
        int size = b.length;
        for (int n = 0; n < size; n++) {
            sb.append("0123456789ABCDEF".charAt(0xF & b[n] >> 4)).append("0123456789ABCDEF".charAt(b[n] & 0xF))/*.append(" ")*/;
        }
        return sb.toString();
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


    /**
     * 字符串BCD码转16进制
     *
     * @param length 字节长度
     */
    public static String getHexFromBCDStr(String str, Integer length) {
        return String.format("%0" + 2 * length + "x", str).toUpperCase();
    }


    /**
     * 字符串BCD码转16进制（低字节在前）
     *
     * @param length 字节长度
     */
    public static String getLowHexFromBCDStr(String str, Integer length) {
        String content = String.format("%0" + 2 * length + "x", str).toUpperCase();
        return exchangeBytes(content);
    }


    /**
     * 十进制转16进制
     *
     * @param length 字节长度
     */
    public static String getHexFromDec(Integer dec, Integer length) {
        return String.format("%0" + 2 * length + "x", dec).toUpperCase();
    }


    /**
     * 十进制转16进制（低字节在前）
     *
     * @param length 字节长度
     */
    public static String getLowHexFromDec(Integer dec, Integer length) {
        return exchangeBytes(getHexFromDec(dec, length));
    }


    /**
     * 16进制转10进制
     */
    public static Integer getDecFromHex(String hex) {
        return Integer.valueOf(hex, 16);
    }

    /**
     * 16进制转10进制（低字节在前）
     */
    public static Integer getDexFromLowHex(String hex) {
        return Integer.valueOf(exchangeBytes(hex), 16);
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
     * 十进制转2进制（高位在前）
     */
    public static String getBinFromDec(Integer dec) {
        return Integer.toBinaryString(dec);
    }

    /**
     * 十进制转2进制，固定长度
     */
    public static String getBinLFromDec(Integer dec, Integer length) {
        if (dec > Math.pow(2, length)) {
            return null;
        }
        StringBuilder binFromDec = new StringBuilder(getBinFromDec(dec));
        while (binFromDec.length() < length) {
            binFromDec.insert(0, "0");
        }
        return binFromDec.toString();
    }


    /**
     * 十进制转2进制，固定长度
     * 低位在前
     */
    public static String getLowBin(String bins) {
        StringBuilder stringBuilder = new StringBuilder(bins);
        StringBuilder reverse = stringBuilder.reverse();
        return reverse.toString();
    }


    /**
     * 2进制转16进制
     */
    public static String getHexFromBin(String bins) {
        if (bins.length() % 8 != 0) {
            return null;
        }
        return Integer.toHexString(Integer.parseInt(bins, 2));
    }

    public static void main(String[] args) {
        int i = Integer.parseInt("0C00",16);
        System.out.println(Integer.toBinaryString(i));
    }
}
