package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CarStock {
    //统计一共生产了多少辆车
    private static int count = 0;
    //存放CarData对象的共享缓冲区
    private BlockingQueue<CarData> queue;

    public CarStock(BlockingQueue<CarData> queue) {
        this.queue = queue;
    }

    //生产车
    public synchronized void productCar() {
        try {
            CarData carData = new CarData();
            //向CarData队列增加一个CarData对象
            boolean success = this.queue.offer(carData, 2, TimeUnit.SECONDS);
            if (success) {
                int id = ++count;
                carData.setId(id);
                System.out.println("生产CarData，编号：" + id + "，库存：" + queue.size());
                Thread.sleep((int) (1000 * Math.random()));
                notifyAll();
            } else {
                System.out.println("生产CarData失败....");
            }
            if (queue.size() < 100) {
            } else {

                System.out.println("库存已满,等待消费...");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //消费车
    public synchronized void resumeCar() {
        try {
            // 从CarData队列中，拿走一个CarData对象
            CarData carData = this.queue.poll(2, TimeUnit.SECONDS);
            if (carData != null) {
                Thread.sleep((int) (1000 * Math.random()));
                notifyAll();
                System.out.println("消费CarData，编号：" + carData.getId() + "，库存: " + queue.size());
            } else {
                System.out.println("消费CarData失败....");
            }
            if (queue.size() > 0) {

            } else {
                System.out.println("库存为空,等待生产...");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
