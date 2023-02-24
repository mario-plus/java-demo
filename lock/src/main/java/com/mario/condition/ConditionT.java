package com.mario.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zxz
 * @date 2023年02月24日 11:24
 * Lock---condition
 * 需求说明：
 * 项目需要反复执行一个任务，
 * 当发生异常时，停止执行（3分钟后再执行）
 * 任务停止执行期间，允许用户手动启动任务
 * 或者3分钟后自动再执行
 */
public class ConditionT {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
//        可以启用多个线程测试，锁的竞争
//        for (int i = 0; i < 3; i++) {
//        }

        //任务线程
        new Thread(() -> {
            lock.lock();
            while (true) {
                String name = Thread.currentThread().getName();
                System.out.println(name + "do business...");
                try {
                    int j = 10 / 0;
                } catch (Exception e) {
                    System.out.println(name + "-任务异常或自动挂起......");
                    try {
                        condition.await(3, TimeUnit.MINUTES);//此处已经释放锁了，await时间结束后，会再去竞争锁
                        System.out.println(name + "-被唤醒，继续运行......");//被唤醒了，说明就又获得了锁，才能执行后续代码，后续代码不做锁释放操作
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }, "任务线程" + "-").start();

        //模拟手动唤醒
        new Thread(() -> {
            while (true) {
                if (lock.getHoldCount() == 0 && lock.tryLock()) {
                    try {
                        System.out.println("唤醒线程获取锁");
                        condition.signal();
                        System.out.println("*****************唤醒condition");
                    } finally {
                        System.out.println("释放锁");
                        lock.unlock();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        }, "唤醒线程").start();
    }


}
/**
 * 执行结果
 * 任务线程0-do business...
 * 任务线程0--任务异常或自动挂起......
 * 唤醒线程获取锁
 * *****************唤醒condition
 * 释放锁
 * 任务线程1-do business...
 * 任务线程1--任务异常或自动挂起......
 * 任务线程2-do business...
 * 任务线程2--任务异常或自动挂起......
 * 任务线程0--被唤醒，继续运行......
 * 任务线程0-do business...
 * 任务线程0--任务异常或自动挂起......
 * 唤醒线程获取锁
 * *****************唤醒condition
 * 释放锁
 * 任务线程1--被唤醒，继续运行......
 * 任务线程1-do business...
 * 任务线程1--任务异常或自动挂起......
 * 唤醒线程获取锁
 * *****************唤醒condition
 * 释放锁
 * 任务线程2--被唤醒，继续运行......
 * 任务线程2-do business...
 * 任务线程2--任务异常或自动挂起......
 * 唤醒线程获取锁
 * *****************唤醒condition
 * 释放锁
 * 任务线程0--被唤醒，继续运行......
 * 任务线程0-do business...
 * 任务线程0--任务异常或自动挂起......
 * 唤醒线程获取锁
 * *****************唤醒condition
 * 释放锁
 * 任务线程1--被唤醒，继续运行......
 * 任务线程1-do business...
 * 任务线程1--任务异常或自动挂起......
 * 唤醒线程获取锁
 * *****************唤醒condition
 * 释放锁
 * 任务线程2--被唤醒，继续运行......
 * 任务线程2-do business...
 * 任务线程2--任务异常或自动挂起......
 */