package com.mario;

/**
 * @author zxz
 * @date 2023年02月20日 10:25
 * 多个线程同时操作共享资源
 */
public class Reason {
    private static Integer num = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> num--).start();
        }
        System.out.println("final num:" + num);
    }
}
