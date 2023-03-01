package com.mario.service.impl;

import com.mario.annoation.BoomComponent;
import com.mario.service.BoomBusinessService;

/**
 * @author zxz
 * @date 2023年03月01日 14:20
 */
@BoomComponent(value = "boomBusinessComponentImpl")
public class BoomBusinessComponentImpl implements BoomBusinessService {
    @Override
    public void sayHello() {
        System.out.println("hello boom component");
    }
}
