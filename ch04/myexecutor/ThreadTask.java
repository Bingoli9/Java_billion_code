package myexecutor;

import java.util.concurrent.Callable;

public class ThreadTask implements Callable<String> {
    private String tname;

    public ThreadTask(String tname) {
        this.tname = tname;
    }

    @Override
    public String call() throws Exception {
        //获取当前线程的名字
        String name = Thread.currentThread().getName();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(name + " - 【" + tname + "】 启动时间：" + currentTimeMillis);
        //模拟线程执行...
        Thread.sleep((long) Math.random() * 2000);
        System.out.println(name + " - 【" + tname + "】 正在执行...");
        return name + " - 【" + tname + "】";
    }
}
