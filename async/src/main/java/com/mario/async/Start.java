package com.mario.async;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zxz
 * @date 2023年05月08日 14:20
 */
@Service
public class Start {

    @Autowired
    AsyncTest asyncTest;

    @PostConstruct
    public void start() throws Exception {
        System.out.println("execute......");
        asyncTest.test();
        System.out.println("execute success......");


        System.out.println("execute void......");
        asyncTest.testVoid();
        System.out.println("execute void success......");
    }

}
