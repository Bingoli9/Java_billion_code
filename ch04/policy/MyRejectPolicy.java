package myexecutor.user.define;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


public class MyRejectPolicy implements RejectedExecutionHandler {
    public MyRejectPolicy() {
    }

    //自定义拒绝方法
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("被拒绝的线程名:" + ((MyThread) r).getThreadName());
    }
}
