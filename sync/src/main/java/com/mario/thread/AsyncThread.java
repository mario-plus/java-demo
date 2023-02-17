package com.mario.thread;

/**
 * @author zxz
 * @date 2023年02月17日 9:47
 * @Des:    run()只是一个方法体（线程体），start()才是真正启动一个线程（就绪状态），获取到CPU时间片就执行
 *
 */
public class AsyncThread extends Thread{

    @Override
    public void run() {
        exe();
    }

    public static void exe() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current name is "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        AsyncThread asyncThread = new AsyncThread();
        asyncThread.start();
        //asyncThread.run();
        System.out.println("````````````````````````");
    }


}
