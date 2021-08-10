package dependency;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

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
        //创建8个消费者，分别设置DataEvent中的data1-data8
        Consumer1 h1 = new Consumer1();
        Consumer2 h2 = new Consumer2();
        Consumer3 h3 = new Consumer3();
        Consumer4 h4 = new Consumer4();
        Consumer5 h5 = new Consumer5();
        Consumer6 h6 = new Consumer6();
        Consumer7 h7 = new Consumer7();
        Consumer8 h8 = new Consumer8();
        //h1、h3、h7并发执行
        disruptor.handleEventsWith(h1, h3, h7);
        //h2在h1之后
        disruptor.after(h1).handleEventsWith(h2);
        //h4在h3之后
        disruptor.after(h3).handleEventsWith(h4);
        //h7在h8之后
        disruptor.after(h7).handleEventsWith(h8);
        //当h2、h4、h8执行完毕后再执行h5，当h5执行完毕后再执行h6
        disruptor.after(h2, h4, h8).handleEventsWith(h5).handleEventsWith(h6);
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