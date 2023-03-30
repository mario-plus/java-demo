package com.mario.mock;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zxz
 * @date 2023年03月22日 14:33
 */
@Builder
@Data
public class Device {

    private Long id;

    private String deviceName;

    public static void main(String[] args) {
        LocalTime of = LocalTime.of(0, 12, 0);
        String format = of.format(DateTimeFormatter.ISO_TIME);
        System.out.println(format);
    }
}
