package com.mario.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zxz
 * @date 2023年05月08日 14:07
 */
@SpringBootApplication
@EnableAsync
public class SpringAsyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAsyncApplication.class, args);
    }
}
