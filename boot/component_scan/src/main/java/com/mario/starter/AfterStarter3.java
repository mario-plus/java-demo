package com.mario.starter;

import com.mario.service.impl.BoomBusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年03月01日 15:25
 */
@Component
public class AfterStarter3 implements ApplicationRunner {

    @Autowired
    BoomBusinessServiceImpl boomBusinessService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner:");
        boomBusinessService.sayHello();
    }
}
