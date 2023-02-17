package com.mario.completableFuture;
import com.mario.thread.AsyncThread;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zxz
 * @date 2023年02月17日 11:08
 */
public class AsyncCompleteFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("当前线程：" + Thread.currentThread().getName());

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            AsyncThread.exe();
            return "finish";
        });
        stringCompletableFuture.thenAcceptAsync((r) -> {
            System.out.println("上一步执行结果：" + r);
            AsyncThread.exe();
            System.out.println("1111111111111111");
        });

        stringCompletableFuture.thenRunAsync(() -> {
            AsyncThread.exe();
            System.out.println("````````````````````````````");
        });
        System.out.println("``````````````````end``````````````````````````");

        /**
         * 在主线程任务执行完以后，如果异步线程执行任务还没执行完就会直接把线程清除掉，
         * 因为默认线程池中的都是守护线程ForkJoinPool，当没有用户线程以后，会随着jvm一起清除
         * */


        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            return 1;
        });
        CompletableFuture<String> re = integerCompletableFuture.thenCombine(integerCompletableFuture1, (x, y) -> x + y + "rerere");
        System.out.println(re.get());

        Thread.sleep(10000);
    }


}
