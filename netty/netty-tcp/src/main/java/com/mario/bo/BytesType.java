package com.mario.bo;

/**
 * @author zxz
 * @date 2023年03月07日 14:17
 */
public interface BytesType {

    Integer TYPE_DEFAULT = 0;

    //4字节转IP字符串
    Integer TYPE_IP = 1;

    //(1)字节16进制转10进制
    Integer TYPE_HEX_TO_DEC = 2;

    //(2)字节转10进制（高字节在前）
    Integer TYPE_HEX_TO_DEC_HIGH = 3;

    //(2)字节转10进制（低字节在前）
    Integer TYPE_HEX_TO_DEC_LOW = 4;

    //多字节转10进制Float（低字节在前）
    Integer TYPE_HEX_TO_FLOAT_LOW = 5;



}
