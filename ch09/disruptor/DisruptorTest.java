package com.yanqun.disruptor.base;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

/**
 * @author yanqun
 * @create 2018/8/27  10:28
 */
public class DisruptorTest {
    public static void main(String[] args) throws InterruptedException {
        //创建若干个新的消费者线程
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread( null, r, "线程名");
            }
        };
        // 通过factory批量产生数据
        DataEventFactory dataFactory = new DataEventFactory();
        // 设置RingBuffer的长度（一般为2的n次方）
        int ringBufferSize = 256;
        /*
            构建disruptor对象，构造方法的部分参数含义如下：
            ProducerType.SINGLE：表名有只有一个生产者向RingBuffer发布数据；如果有多个生产者，则需要使用ProducerType.MULTI
            new YieldingWaitStrategy()：当生产和消费的速度不一致时的一种等待策略。所有的等待策略都需要实现WaitStrategy接口。
          */
        Disruptor<DataEvent> disruptor = new Disruptor<>(dataFactory, ringBufferSize, threadFactory, ProducerType.SINGLE,new YieldingWaitStrategy());
        // 绑定消费者DataEventHandler
        disruptor.handleEventsWith(new DataEventHandler());
        //启动 Disruptor, 之后当生产者给RingBuffer中增加数据Event并publish()时，就会自动触发消费者中的onEvent()方法，进行消费
        disruptor.start();
        // 获取RingBuffer
        RingBuffer<DataEvent> ringBuffer = disruptor.getRingBuffer();
        //获取关联RingBuffer的生产者
        DataEventProducer producer = new DataEventProducer(ringBuffer);
        //申请2个字节的内存空间(因为本示例所传递的数据DataEvent类中仅仅包含一个char类型，而一个char正好占2个字节)
        ByteBuffer buffer = ByteBuffer.allocate(2);
        for (int i=0;i<1000000;i++) {
            //给刚刚申请的内存空间存放数据'a'
            buffer.putChar(0, 'a');
            //生产并发布数据'a'
            producer.product(buffer);
        }
    }
}
