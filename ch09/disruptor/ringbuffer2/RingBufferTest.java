package ringbuffer2;

import base.DataEventHandler;
import com.lmax.disruptor.*;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RingBufferTest {
    public static void main(String[] args) throws InterruptedException {
        int ringBufferSize = 1024*1024;
        //1个生产者+6个消费者
        int threadsNum = 7;

        RingBuffer<DataEvent> ringBuffer = RingBuffer.createSingleProducer(()->new DataEvent(), ringBufferSize);
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        //线程池
        ExecutorService executor = Executors.newFixedThreadPool(threadsNum);
        WorkHandler<DataEvent> handler1 = new Data1EventHandler();
        WorkHandler<DataEvent> handler2 = new Data2EventHandler();
        WorkHandler<DataEvent> handler3 = new Data3EventHandler();
        //创建3个消费者线程
        //创建WorkerPool对象，该对象封装了消费者、缓冲区等信息
        WorkerPool<DataEvent> workerPool1 = new WorkerPool<DataEvent>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), handler1,handler2,handler3);
        workerPool1.start(executor);


        long sequence;
        for (int i = 0; i < 5; i++) {
            sequence = ringBuffer.next();
            ringBuffer.get(sequence).setData0("message0");
            ringBuffer.publish(sequence);
        }
        Thread.sleep(1000);
        //workerPool.halt();
        //executor.shutdown();
    }
}