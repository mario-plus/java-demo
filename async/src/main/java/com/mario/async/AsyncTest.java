package com.mario.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年05月08日 14:18
 */
@Component
public class AsyncTest {
    /**
     * value:线程池名称（默认使用）{@link org.springframework.core.task.TaskExecutor TaskExecutor}
     * 加上注解，仍是同步执行的原因：
     * 1. 未开启@EnableAsync
     * 2. 同类调用
     * 3. 非public方法(IDEA会提示)
     * 4. 非静态方法(IDEA会提示)
     * 5. 无返回值
     */
    @Async
    public String test() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("执行成功");
        return "执行完毕......";
    }


    @Async
    public void testVoid() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("执行成功");
    }

//    @Async
//    private String testPrivate() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
//        Thread.sleep(2000);
//        System.out.println("执行成功");
//        return "";
//    }
//
//    @Async
//    public static String testStatic() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
//        Thread.sleep(2000);
//        System.out.println("执行成功");
//        return "";
//    }

}
