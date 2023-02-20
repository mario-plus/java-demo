package com.mario;

/**
 * @author zxz
 * @date 2023年02月20日 10:29
 * <p>
 * 1.修饰方法
 */
public class Sync {

    private static Integer class_lock_num_1 = 1000;

    private Integer instance_lock_num_2 = 1000;

    private Integer instance_method_lock_num_3 = 1000;


    public static void main(String[] args) throws InterruptedException {
        //类锁
        for (int i = 0; i < 1000; i++) {
            new Thread(Sync::decrease).start();
        }
        Thread.sleep(1000);/*等待1s，保证所有线程都执行完*/
        System.out.println("final num " + class_lock_num_1);


        //实例锁
        Sync sync = new Sync();
        for (int i = 0; i < 1000; i++) {
            new Thread(sync::doDecrease).start();
        }
        Thread.sleep(1000);
        System.out.println("final num " + sync.instance_lock_num_2);

        for (int i = 0; i < 1000; i++) {
            new Thread(sync::doDecrease2).start();
        }
        Thread.sleep(1000);
        System.out.println("final num " + sync.instance_method_lock_num_3);

    }

    /**
     * 类锁
     */
    public static synchronized void decrease() {
        class_lock_num_1--;
    }

    /**
     * 实例锁
     */
    public void doDecrease() {

        synchronized (this) {
            instance_lock_num_2--;
        }
    }


    /**
     * 实例锁：方法锁
     */
    public synchronized void doDecrease2() {
        instance_method_lock_num_3--;
    }
}
