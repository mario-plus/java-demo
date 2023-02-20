package com.mario;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;


import java.util.UUID;
import java.util.concurrent.Executors;


/**
 * @author zxz
 * @date 2023年02月20日 15:37
 */
public class DisruptorT {

    public static void main(String[] args) {

        Disruptor<? extends Element> disruptor = new Disruptor<>(Element::new,
                1024,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        disruptor.handleEventsWith((EventHandler<Element>) (event, sequence, endOfBatch) -> dealUpMsg(event.getRequestId(), event.getMsg()));
        disruptor.start();


        /**
         * 线程每秒存1000次数据
         * */
        new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                RingBuffer<? extends Element> ringBuffer = disruptor.getRingBuffer();
                long next = ringBuffer.next();
                Element element = ringBuffer.get(next);
                element.setRequestId(UUID.randomUUID().getLeastSignificantBits());
                element.setMsg(Thread.currentThread().getName() + "good " + j);
                ringBuffer.publish(next);
            }
        }).start();

    }

    private static void dealUpMsg(Long requestId, String msg) throws InterruptedException {
        System.out.println("receive msg:" + msg + ",requestId:" + requestId);
    }


}
