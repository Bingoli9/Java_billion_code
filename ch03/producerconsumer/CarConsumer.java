package producerconsumer;

//消费者
public class CarConsumer implements Runnable {
    //共享缓存区：CarData队列
    private CarStock carPool;

    public CarConsumer(CarStock carPool) {
        this.carPool = carPool;
    }

    @Override
    public void run() {
        while (true) {
            carPool.resumeCar();
        }
    }
}
