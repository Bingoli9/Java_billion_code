//car的库存
class CarStock {
    //最多能存放20辆车
    int cars;

    //通知生产者去生产车
    public synchronized void productCar() {
    try {
        if (cars <20) {
              System.out.println("生产车...."+ cars);
              Thread.currentThread().sleep(100);
              cars++;
              //通知正在监听CarStock并且处于阻塞状态的线程（即处于wait()状态的消费者）
                notifyAll();

         } else {//超过了最大库存20
                //使自己（当前的生产者线程）处于阻塞状态，等待消费者消执行car--(即等待消费者调用notifyAll()方法)
                wait();

        }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    public synchronized void resumeCar() {//通知消费者去消费车
        try {
             if (cars >0) {
                 System.out.println("销售车...."+ cars);
                 Thread.currentThread().sleep(100);
                 cars--;
                 notifyAll();
                //通知正在监听CarStock并且处于阻塞状态的线程（即处于wait()状态的生产者）
              } else {
                //使自己（当前的消费者线程）处于阻塞状态，等待消费者消执行car++(即等待生产者调用notifyAll()方法)
                wait();
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}

//生产者
class CarProducter implements Runnable {
      CarStock carStock;
      public CarProducter(CarStock clerk) {
          this.carStock = clerk;
      }

    @Override
    public void run() {
        while (true) {
            carStock.productCar(); //生产车
        }
    }
}

//消费者
class CarConsumer implements Runnable {
     CarStock carStock;

    public CarConsumer(CarStock carStock) {
       this.carStock = carStock;
    }

    @Override
    public void run() {
        while (true) {
            carStock.resumeCar();//消费车
        }
    }
}

//测试方法
public class ProducerAndConsumer {
    public static void main(String[] args) {
        CarStock carStock = new CarStock();
        //注意：生产者线程和消费者线程，使用的是同一个carStock对象
        CarProducter product = new CarProducter(carStock);
        CarConsumer resumer = new CarConsumer(carStock);
        //2个生产者，2个消费者
        Thread tProduct1 = new Thread(product);
        Thread tProduct2 = new Thread(product);
        Thread tResumer1 = new Thread(resumer);
        Thread tResumer2 = new Thread(resumer);
        tProduct1.start();
        tProduct2.start();
        tResumer1.start();
        tResumer2.start();
    }
}