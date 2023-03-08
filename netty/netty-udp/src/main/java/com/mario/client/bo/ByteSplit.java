package com.mario.client.bo;

import lombok.Data;

/**
 * @author zxz
 * @date 2023年03月07日 10:13
 */
@Data
public class ByteSplit {

    private Integer startIndex;

    private Integer length;

    private String key;

    private String des;

    /**
     * {@link BytesType}
     * 处理数据类型
     */
    private Integer type;

    /**
     * 最终数据类型（Integer，String，double......）
     */
    private Integer targetType;


    private Object value;

    public ByteSplit(Integer startIndex, Integer length, String key, String des) {
        this.startIndex = startIndex;
        this.length = length;
        this.key = key;
        this.des = des;
        this.type = BytesType.TYPE_DEFAULT;
        this.targetType = DataType.DATA_TYPE_STRING;
    }

    public ByteSplit(Integer startIndex, Integer length, String key, String des, Integer type) {
        this.startIndex = startIndex;
        this.length = length;
        this.key = key;
        this.des = des;
        this.type = type;
        this.targetType = DataType.DATA_TYPE_STRING;
    }


    public ByteSplit(Integer startIndex, Integer length, String key, String des, Integer type, Integer targetType) {
        this.startIndex = startIndex;
        this.length = length;
        this.key = key;
        this.des = des;
        this.type = type;
        this.targetType = targetType;
    }
}
