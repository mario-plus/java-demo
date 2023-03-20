package com.mario.client.handler;

import com.mario.client.bo.ByteSplit;
import com.mario.client.bo.UvpConstants;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月10日 13:56
 */
public class UvpMsgService extends ByteSplitService {


    @Override
    protected List<ByteSplit> splitBytes(List<ByteSplit> byteSplits, String content) {
        System.out.println("上行数据：" + content);
        if (!content.startsWith(UvpConstants.resHead) || content.length() < 7) {
            return null;
        }
        String substring = content.substring(4, 6);
        String type = null;
        if (UvpConstants.hex.equals(substring)) {
            type = UvpConstants.hex;
        } else if (UvpConstants.dec.equals(substring)) {
            type = UvpConstants.dec;
        }
        if (type == null) {
            return null;
        }
        content = content.replace("\r\n", "");//去除转义字符
        String data = content.substring(7, content.length() - 1);//响应数据体
        System.out.println("数据体：" + data);
        return parseData(type, byteSplits, data);
    }

    private List<ByteSplit> parseData(String type, List<ByteSplit> byteSplits, String data) {
        String[] split = data.split(",");
        if (split.length != byteSplits.size()) {//上行数据与数据格式不一致
            return null;
        }
        byteSplits.forEach(e -> {
            String s = split[e.getStartIndex() - 1];
            if (UvpConstants.hex.equals(type)) {
                s = String.valueOf(Integer.parseInt(s, 16));
            }
            e.setValue(s);
        });
        return byteSplits;
    }


}
