package com.mario.client.handler;


import com.mario.client.bo.ByteSplit;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月07日 10:15
 */
public abstract class ByteSplitService {

    Integer bit = 2;

    protected abstract List<ByteSplit> splitBytes(List<ByteSplit> byteSplits, String content);

}

