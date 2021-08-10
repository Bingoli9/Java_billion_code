package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) {
        //创建一个Callable类型的线程对象
        MyCallableThread myThread = new MyCallableThread();
        //将线程对象包装成FutureTask对象，用于接收线程的返回值
        FutureTask<Integer> result = new FutureTask<>(myThread);
        //运行线程
        new Thread(result).start();
        //通过FutureTask的get()接收myThread的返回值
        try {
            Integer sum = result.get();//以闭锁的方式，获取线程的返回值
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyCallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("线程运行中...计算1-100之和");
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}