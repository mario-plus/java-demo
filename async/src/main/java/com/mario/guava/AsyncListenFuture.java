package com.mario.guava;

import com.google.common.util.concurrent.*;
import com.mario.thread.AsyncThread;


import java.util.concurrent.Executors;

/**
 * @author zxz
 * @date 2023年02月17日 11:26
 */
public class AsyncListenFuture {

    public static void main(String[] args) {

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(6));
        ListenableFuture<String> submit = listeningExecutorService.submit(() -> {
            AsyncThread.exe();
            return "finish";
        });
        Futures.addCallback(submit, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("执行结果：" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, listeningExecutorService);
    }


}
