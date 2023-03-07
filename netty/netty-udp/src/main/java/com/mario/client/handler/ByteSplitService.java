package com.mario.client.handler;


import com.mario.client.bo.ByteSplit;
import com.mario.client.utils.ByteUtil;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月07日 10:15
 */
public interface ByteSplitService {

    Integer bit = 2;

    default List<ByteSplit> splitBytes(List<ByteSplit> byteSplits, String content) {
        if (!ByteUtil.checkSplitRule(byteSplits, content)) {
            //规则解析异常
        }
        return ByteUtil.doSplitByte(byteSplits, content);
    }


}

