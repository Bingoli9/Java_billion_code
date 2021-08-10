package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // 同一时间，只允许3个线程并发访问
        Semaphore semp = new Semaphore(3);
        // 创建10个线程
        for (int i = 0; i < 8; i++) {
            final int threadNo = i;
            //execute()方法的参数：重写了run()方法的Runnable对象
            executor.execute(() -> {
                        try {
                            //同一时间，只能有3个线程获取许可去执行
                            semp.acquire();
                            System.out.println("得到许可并执行的线程: " + threadNo);
                            Thread.sleep((long) (Math.random() * 10000));
                            // 得到许可的线程执行完毕后，将许可转让给其他线程
                            semp.release();
                        } catch (InterruptedException e) {
                        }
                    }
            );
        }
        //  executor.shutdown();
    }

}  