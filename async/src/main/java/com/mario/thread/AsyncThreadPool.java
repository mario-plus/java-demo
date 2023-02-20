package com.mario.thread;

import java.util.concurrent.*;

/**
 * @author zxz
 * @date 2023年02月17日 9:55
 * 线程池作用：
 * 1. 避免频繁的创建，销毁线程，浪费系统资源
 * 2. 避免无限制的创建线程，导致系统资源耗尽
 *
 * 提交线程的方式：
 * submit:返回future对象,get()会阻塞当前线程
 * execute:无返回值
 *
 * ThreadPoolExecutor
 * 参数：
 * 核心线程数，
 * 最大线程数，
 * 空闲时存活时间(时间单位)，
 * 队列（直接提交队列、有界任务队列、无界任务队列、优先任务队列）
 * 饱和策略（AbortPolicy：拒绝并抛出异常；CallerRunsPolicy：使用当前调用的线程来执行此任务；DiscardOldestPolicy：抛弃队列头部（最旧）的一个任务，并执行当前任务；DiscardPolicy：忽略并抛弃当前任务），
 * 线程工厂(用来创建线程)
 *
 * 工作原理：
 * 核心线程数未满，提交任务，处理
 * 核心线程数已满，提交任务（入队），等待线程处理
 * 核心线程数已满，且队列已满，创建新的线程处理，
 * 核心线程数已满，且队列已满，线程达最大线程数，使用饱和策略（拒绝策略）
 *
 * 线程池：shutdown与shutdownNow
 *shutdown：关闭线程池（shutdown状态），不再接收新任务，并执行完队列任务
 *shutdownNow：关闭线程池（stop状态），停止执行任务，并返回队列中任务列表
 *
 * 线程池五种状态：
 * RUNNING
 * STOP
 * SHUTDOWN
 * TIDYING（整理）
 * TERMINATED(终止)
 */
public class AsyncThreadPool {
    /*ThreadPoolExecutor自定义线程池参数*/
    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                6,
                60,
                TimeUnit.SECONDS
                , new LinkedBlockingDeque<Runnable>()
                , new ThreadPoolExecutor.DiscardOldestPolicy());


        Future<?> submit = executorService.submit(new Runnable() {
            public void run() {
                AsyncThread.exe();
                System.out.println("123");
            }
        });

        //submit.get();
        executorService.execute(new Runnable() {
            public void run() {
                AsyncThread.exe();
                System.out.println("456");
            }
        });
        System.out.println("`````````````````````````````````````");
    }

}
