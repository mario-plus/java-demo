package com.mario.metadata.up;

import com.mario.metadata.Element;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月28日 9:58
 */
@Data
public class UpInfo {

    /**
     * 物模型key
     */
    private String cmd;

    /**
     * 预留字段
     * 属性，遥测，事件，服务
     */
    private String cmdType;

    /**
     * 解析模板
     */
    private List<Element> body = new ArrayList<>();


    /**
     * 单个物模型转换器
     */
    private String cmdConvert;


}
