package com.mario.test;

/**
 * @author zxz
 * @date 2023年05月19日 18:11
 */
public class EdenSurvivorTest {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
               new tt();
            }
        }, "循环线程").start();


    }
    static class tt{
        private String name;
        private String data;
    }
}