package com.mario.service.impl;

import com.mario.annoation.BoomService;
import com.mario.service.BoomBusinessService;

/**
 * @author zxz
 * @date 2023年03月01日 14:17
 */
@BoomService(value = "boomBusinessServiceImpl")
public class BoomBusinessServiceImpl implements BoomBusinessService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
