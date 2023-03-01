package com.mario;

import com.mario.annoation.EnableBoom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxz
 * @date 2023年03月01日 10:41
 */
@SpringBootApplication
@EnableBoom
public class ComponentScanApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComponentScanApplication.class, args);
    }
}
