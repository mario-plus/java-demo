package com.mario.future;

import com.mario.thread.AsyncThread;

import java.util.concurrent.*;

/**
 * @author zxz
 * @date 2023年02月17日 10:47
 *
 * Future不足之处：
 * 1. 主线程无法感知任务执行完成与否，调用get()方法会阻塞主线程
 * 2. 每一个Future都是彼此独立的
 * 3. 无异常处理机制
 *
 * runnable和callable区别
 * 1. runnable无返回值，callable有返回值
 * 2. runnable异常只能内部try-catch处理，不能向外抛出，callable可以向外抛异常
 */
public class AsyncFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                AsyncThread.exe();
                return "finish";
            }
        });
        String s = future.get();
        System.out.println("`````````````````````" + s);
    }
}
