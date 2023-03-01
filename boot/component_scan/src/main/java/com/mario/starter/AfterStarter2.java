package com.mario.starter;

import com.mario.service.impl.BoomBusinessComponentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zxz
 * @date 2023年03月01日 15:01
 */
@Component
public class AfterStarter2 {

    @Autowired
    BoomBusinessComponentImpl boomBusinessComponent;

    @PostConstruct
    void init(){
        System.out.println("PostConstruct");
        boomBusinessComponent.sayHello();
    }

}
