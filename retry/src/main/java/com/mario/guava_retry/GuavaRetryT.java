package com.mario.guava_retry;

import com.github.rholder.retry.*;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zxz
 * @date 2023年02月24日 16:10
 */
public class GuavaRetryT {
    public static void main(String[] args) {


        Retryer<Integer> build = RetryerBuilder.<Integer>newBuilder()
                .retryIfException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(100))
                .withBlockStrategy(BlockStrategies.threadSleepStrategy())
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        System.out.println("每一次调用后执行，相当于回调");
                        System.out.println(attempt.hasException());
                    }
                })
                .build();
        try {
            build.call(() -> {
                int i = new Random().nextInt(100);
                System.out.println("execute,随机数：" + i);
                if (i == 0 || i % 87 != 0) {
                    throw new Exception("调用失败");
                }
                return null;
            });
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }


    }
}
