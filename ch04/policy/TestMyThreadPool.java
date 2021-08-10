package myexecutor.user.define;

import java.util.concurrent.*;


public class TestMyThreadPool {
    public static void main(String[] args) {
		/*
			核心线程:1，如果只有1个任务，会直接交给线程池中的这一个线程来处理
			最大线程数：2，如果任务的数量>核心线程数1+workQueue.size()，且任务的数量<=大线程数2+workQueue.size()之和时,就将新提交的任务交给非核心线程处理
			最大空闲时间:10秒
			任务队列：有界队列ArrayBlockingQueue，该队列中可以存放3个任务
			拒绝策略：AbortPolicy()，	当提交任务数>最大线程数2+workQueue.size()之和时，任务会交给AbortPolicy()来处理
		 */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>(),
                //new ArrayBlockingQueue<Runnable>(3),
                 new MyRejectPolicy()
//                new ThreadPoolExecutor.AbortPolicy()
        );
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");
        MyThread t4 = new MyThread("t4");
        MyThread t5 = new MyThread("t5");
        MyThread t6 = new MyThread("t6");
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.shutdown();
    }
}
