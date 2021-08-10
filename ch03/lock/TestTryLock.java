package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TestTryLock extends Thread {
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        boolean isLocked = false;
        String currentThreadName = Thread.currentThread().getName();
        try {
            //在5毫秒的时间内，始终尝试加锁
            isLocked = lock.tryLock(5, TimeUnit.MILLISECONDS);
            System.out.println(currentThreadName + "-尝试加锁： " + isLocked);
            if (isLocked) {
                //如果尝试成功，则加锁
                Thread.sleep(20);
                System.out.println(currentThreadName + "-加锁成功");
            } else {
                System.out.println(currentThreadName + "-加锁失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isLocked) {
                System.out.println(currentThreadName + "-解锁");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TestTryLock t1 = new TestTryLock();
        TestTryLock t2 = new TestTryLock();
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
