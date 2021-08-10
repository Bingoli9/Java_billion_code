package ringbuffer1;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RingBufferTest {
    public static void main(String[] args) throws Exception {
        //设置RingBuffer大小
        int ringBufferSize = 1024*1024;
        //设置线程的数量（其中有1个生产者线程，3个消费者线程）
        int threadsNum = 4;
        /*
         * createSingleProducer()：创建一个单生产者的RingBuffer，该方法有3个参数：
         * 第1个参数：参数类型是 EventFactory<E>，即事件工厂；和之前我们自己编写的DataEventFactory类似，用于向RingBuffer中产生大量Event数据
         * 第2个参数：参数类型是int，用于设置RingBuffer的大小
         * 第3个参数：参数类型是WaitStrategy，等待策略
         */
        RingBuffer<DataEvent> ringBuffer = RingBuffer.createSingleProducer(()-> new DataEvent(), ringBufferSize, new YieldingWaitStrategy());
        //创建一个可以存放4个线程的线程池
        ExecutorService executors = Executors.newFixedThreadPool(threadsNum);
        //创建SequenceBarrier，用于协调生产和消费的速度
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        //创建第一个消费者线程
        //创建消息处理器对象，该对象封装了消费者、缓冲区等信息
        BatchEventProcessor<DataEvent> dataProcessor1 = new BatchEventProcessor<DataEvent>(ringBuffer, sequenceBarrier, new Data1EventHandler());
        //把当前线程所代表的消费者正在消费的位置信息，通过RingBuffer告知给生产者；从而让生产者可以感知消费者的速度
        ringBuffer.addGatingSequences(dataProcessor1.getSequence());
        //把消息处理器提交到线程池，即让消费者1以线程的方式去执行
        executors.submit(dataProcessor1);

        //创建第二个消费者线程
        BatchEventProcessor<DataEvent> dataProcessor2 = new BatchEventProcessor<DataEvent>(ringBuffer, sequenceBarrier, new Data2EventHandler());
        ringBuffer.addGatingSequences(dataProcessor2.getSequence());
        executors.submit(dataProcessor2);

        //创建第三个消费者线程
        BatchEventProcessor<DataEvent> dataProcessor3 = new BatchEventProcessor<DataEvent>(ringBuffer, sequenceBarrier, new Data3EventHandler());
        ringBuffer.addGatingSequences(dataProcessor3.getSequence());
        executors.submit(dataProcessor3);

        //提交生产者线程
        Future<?> future = executors.submit(() -> {
                    long sequence;
                    /*
                        每个生产者生产5条数据，并发布到RingBuffer中，供消费者消费。
                        其中生产者所生产的数据类型，是通过RingBuffer.createSingleProducer()方法的第一个参数指定的
                     */
                    for (int i = 0; i < 5; i++) {
                        sequence = ringBuffer.next();
                        DataEvent event = ringBuffer.get(sequence);
                        event.setData0("message0");
                        ringBuffer.publish(sequence);
                    }
                }
        );
        future.get();//等待生产者结束
        Thread.sleep(1000);//模拟其他业务操作
        //dataProcessor.halt();//结束任务
        //executors.shutdown();//结束线程
    }
}