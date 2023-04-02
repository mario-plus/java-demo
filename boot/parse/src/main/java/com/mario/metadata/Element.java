package com.mario.metadata;

import com.mario.constants.ElementTargetType;
import com.mario.constants.ElementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月22日 20:36
 */
@Data
@NoArgsConstructor
public class Element {

    /**
     * 顺序
     */
    private Integer order;


    /**
     * 数据类型（静态属性，动态属性）
     * {@link com.mario.constants.ElementType}
     */
    private String type = ElementType.dynamic;//默认是动态


    /**
     * 目标类型
     * {@link com.mario.constants.ElementTargetType}
     */
    private String targetType = ElementTargetType.stringType;


    /**
     * 动态数组，hex
     * 组内元素长度，字节数
     */
    private Integer arrayLength;

    /**
     * 长度
     * 1.字节
     * 2.位
     * 3.字符串长度
     */
    private Integer length;

    /**
     * 起始下标
     * 1.字符串
     * 2.字节
     */
    private Integer startIndex;


    /**
     * 数据key
     * json数据转化则需要key
     */
    private String key;


    /**
     * 填充值
     * ${}从resource里面去，#{}从自身数据取（最后填充）
     */
    private Object value;


    /**
     * 1.通过beanName.method反射调用，获取单个元素的value
     * 2.调用{@link com.mario.abr.down.IDependDownConvert#doElementDownConvert}
     * 3.调用{@link com.mario.abr.up.ICmdKeyUpConvert#doCmdKeyUpConvert}
     */
    private String elementConvert;

    /**
     * 当${}中的值equal defaultKey，value则用defaultValue填充
     */
    private String defaultKey;


    /**
     * 默认值
     */
    private Object defaultValue;


    /**
     * 上行数据
     * 所属字节标识
     * 所有该标识，都转成2进制，最后转成16进制
     */
    private Integer byteGroup;


    /**
     * 子节点
     */
    private List<Element> elements;


}
