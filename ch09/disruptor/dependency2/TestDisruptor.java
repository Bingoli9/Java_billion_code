package dependency2;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import dependency.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

public class TestDisruptor {
    public static void main(String[] args) throws InterruptedException {
        //创建一个新的消费者线程
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(null, r, "消费线程");
            }
        };

        int bufferSize = 1024 * 2048;
        Disruptor<DataEvent> disruptor = new Disruptor<DataEvent>(() -> new DataEvent(), bufferSize, threadFactory, ProducerType.SINGLE, new BusySpinWaitStrategy());
        //创建3个消费者，分别设置DataEvent中的data1-data3
        Consumer1 h1 = new Consumer1();
        Consumer2 h2 = new Consumer2();
        Consumer3 h3 = new Consumer3();
        //h1、h2并发执行；当h1和h2各自执行完毕后，再执行h3
        EventHandlerGroup<DataEvent> handlerGroup = disruptor.handleEventsWith(h1, h2).then(h3);

        //启动disruptor
        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);
        //启动生产者（生产者会设置DataEvent中的data0）
        threadFactory.newThread(new DataEventProducter(latch, disruptor)).start();
        latch.await();//等待生产者线程执行完毕
        Thread.sleep(3000);//模拟其他业务
        disruptor.shutdown();
    }
}  