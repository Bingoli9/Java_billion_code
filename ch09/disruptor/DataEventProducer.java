package com.yanqun.disruptor.base;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;


public class DataEventProducer {
    private final RingBuffer<DataEvent> ringBuffer;//环形缓冲区
    public DataEventProducer(RingBuffer<DataEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    /**
     * 产生并发布数据；
     * 参数会通过事件传递给消费者，告知消费者去消费数据
     */public void product(ByteBuffer data) {//ByteBuffer：就是charEvent
        //ringBuffer是一个环形数组，而next()用于获取数组中下一个元素的下标；
        long sequence = ringBuffer.next();//环形的下标
        try {
            /*
              根据下一个元素的下标，获取下一个元素值；
              注意，拿到的是空的对象
             */
            DataEvent event = ringBuffer.get(sequence);// 拿到了空的对象
            event.setValue(data.getChar(0)  );//赋值
            System.out.println("生产：" + event.getValue());
        } finally {
            //发布event（即发布数据）；当数据被publish()之后，就可以被消费者（DataEventHandler）的监听器方法onEvent所监听到
            ringBuffer.publish(sequence);//监听器：发布之后，监听者才能获取数据
        }
    }
}
