package com.mario.abr.up;

/**
 * @author zxz
 * @date 2023年04月02日 10:13
 * 用于自定义上行数据key
 */
public abstract class ICmdKeyUpConvert extends IUpConvert<String, byte[], Object> {

    @Override
    protected String doUpConvert(byte[] bytes, Object o) {
        return doCmdKeyUpConvert(bytes);
    }

    public abstract String doCmdKeyUpConvert(byte[] bytes);
}
