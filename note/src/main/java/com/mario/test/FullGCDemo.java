package com.mario.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FullGCDemo {

        private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buildBar();
            Thread.sleep(100);
        }
    }

    private static void buildBar() {
        List<Object> futureContractList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            futureContractList.add(new Object());
        }
        futureContractList.forEach(contract -> {
            executor.scheduleWithFixedDelay(() -> {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 2, 3, TimeUnit.SECONDS);
        });
    }
}
