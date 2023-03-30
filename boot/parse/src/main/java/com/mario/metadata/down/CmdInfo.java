package com.mario.metadata.down;

import com.mario.metadata.Element;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月22日 20:34
 */
@Data
public class CmdInfo {

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
     * 元素列表
     */
    private List<Element> head = new ArrayList<>();

    /**
     * 元素列表
     */
    private List<Element> body= new ArrayList<>();

    /**
     * 元素列表
     */
    private List<Element> tail= new ArrayList<>();

    /**
     * 单个物模型转换器
     */
    private String cmdConvert;

}
