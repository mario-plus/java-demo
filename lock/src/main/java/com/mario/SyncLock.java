package com.mario;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zxz
 * @date 2023年02月20日 11:24
 */
public class SyncLock {

    private Integer lock_num_1 = 1000;
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        SyncLock syncLock = new SyncLock();
        for (int i = 0; i < 1000; i++) {
            new Thread(syncLock::decrease).start();
        }

        Thread.sleep(1000);
        System.out.println("final num: " + syncLock.lock_num_1);
    }

    public void decrease() {
        try {
            reentrantLock.lock();
            lock_num_1--;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }
}
