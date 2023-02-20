package com.mario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年02月20日 17:25
 */
@RestController
public class HelloController {
    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }
}
