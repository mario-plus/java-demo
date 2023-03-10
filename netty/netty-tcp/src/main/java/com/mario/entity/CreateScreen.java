package com.mario.entity;

import com.mario.client.EntityFunctionKey;
import com.mario.client.FunctionKey;
import lombok.Data;

/**
 * @author zxz
 * @date 2023年03月09日 18:31
 */
@Data
public class CreateScreen extends EntityFunctionKey {
    @Override
    protected String getFunctionKey() {
        return FunctionKey.createScreen;
    }

    private String ugid;//用户组id
    private String outslot;//输出槽位
    private String outport;//输出接口
    private String screenx;//显示屏横坐标
    private String screeny;//显示屏纵坐标
    private String screenw;//显示屏宽度
    private String screenh;//显示屏高度
    private String bright; //显示屏亮度
}
