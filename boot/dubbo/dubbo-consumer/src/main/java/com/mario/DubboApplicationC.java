package com.mario;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxz
 * @date 2023年02月28日 10:49
 */
@SpringBootApplication
@EnableDubbo
public class DubboApplicationC {
    public static void main(String[] args) {
        SpringApplication.run(DubboApplicationC.class, args);
    }
}
