package multi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

public class Test {
    public static void main(String[] args) throws Exception {
        RingBuffer<DataEvent> ringBuffer =
                RingBuffer.create(ProducerType.MULTI,
                        () -> new DataEvent(),
                        1024 * 1024,
                        new YieldingWaitStrategy());

        SequenceBarrier barriers = ringBuffer.newBarrier();
        //100个消费者
        Consumer[] consumers = new Consumer[100];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("cus" + i);
        }
        WorkerPool<DataEvent> workerPool =
                new WorkerPool<DataEvent>(ringBuffer,
                        barriers,
                        new DataEventExceptionHandler(),
                        consumers);
        //协调生产者与消费者的速度
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        workerPool.start(executors);
		
        final CountDownLatch latch = new CountDownLatch(1);
        //100个生产者
        for (int i = 0; i < 1000; i++) {
            final Producter p = new Producter(ringBuffer);
            new Thread(
                    () -> {
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int j = 0; j < 100; j++) {
                            p.product("hello");
                        }
                    }
            ).start();
        }
        latch.countDown();
        Thread.sleep(3000);//模拟其他业务
        //统计总消费的数据
        int totalCount = 0;
        for (int i = 0; i < consumers.length; i++) {
            totalCount += consumers[i].getCount();
        }
        System.out.println("总数:" + totalCount);
        workerPool.halt();
        executors.shutdown();
    }

    //disruptor要求：自定义异常必须实现ExceptionHandler接口
    static class DataEventExceptionHandler implements ExceptionHandler {
        public void handleEventException(Throwable ex, long sequence, Object event) {
            System.out.println("执行过程中，出现了异常...");
        }
        public void handleOnStartException(Throwable ex) {
            System.out.println("启动时，出现了异常...");
        }
        public void handleOnShutdownException(Throwable ex) {
            System.out.println("关闭时，出现了异常...");
        }
    }
}
