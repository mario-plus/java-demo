package com.mario.starter;

import com.mario.service.impl.BoomBusinessComponentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年03月01日 15:25
 */
@Component
public class AfterStarter1 implements CommandLineRunner {

    @Autowired
    BoomBusinessComponentImpl boomBusinessComponent;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner:");
        boomBusinessComponent.sayHello();
    }
}
