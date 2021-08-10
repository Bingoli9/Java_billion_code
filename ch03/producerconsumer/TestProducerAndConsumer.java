package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestProducerAndConsumer {

    public static void main(String[] args) throws Exception {
        //共享缓存区：CarData队列
        BlockingQueue<CarData> queue = new LinkedBlockingQueue<CarData>(100);
        //CarData库存，包含了queue队列
        CarStock carStock = new CarStock(queue);
        //生产者
        CarProducter carProducter1 = new CarProducter(carStock);
        CarProducter carProducter2 = new CarProducter(carStock);
        //消费者
        CarConsumer carConsumer1 = new CarConsumer(carStock);
        CarConsumer carConsumer2 = new CarConsumer(carStock);
        //将生产者和消费者加入线程池运行
        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(carProducter1);
        cachePool.execute(carProducter2);
        cachePool.execute(carConsumer1);
        cachePool.execute(carConsumer2);
//		carProducter1.stop();停止p1生产
//		cachePool.shutdown();//关闭线程池 
    }
}
