package com.mario.client.utils;

import com.mario.client.bo.ByteSplit;
import com.mario.client.bo.BytesType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxz
 * @date 2023年03月08日 10:32
 */
public class ColorLightByteUtil {
    final static Integer bit = 2;

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

    public static String allocateDev(Integer num) {
        if (num == 0) {
            return "FFFF";//广播
        } else {
            return ByteUtil.decToHex(num - 1, 2 * bit);//00表示第一台
        }
    }

    /**
     * 格式化数据
     */
    protected static String formatValue(String value, Integer type) {
        if (type.equals(BytesType.TYPE_IP)) {
            return ByteUtil.formatValueToIp(value);
        } else if (type.equals(BytesType.TYPE_HEX_TO_DEC)
                || type.equals(BytesType.TYPE_HEX_TO_DEC_HIGH)
                || type.equals(BytesType.TYPE_HEX_TO_DEC_LOW)) {
            return ByteUtil.formatValueToDEC(value, type);
        } else if (type.equals(BytesType.TYPE_HEX_TO_FLOAT_LOW)) {
            return ByteUtil.formatValueToDecFloat(value);
        }
        return value;
    }


}
