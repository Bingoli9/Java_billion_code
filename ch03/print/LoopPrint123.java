package lock.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoopPrint123 {
    //初始值
    private int number = 1;
    //Lock对象
    private Lock lock = new ReentrantLock();
    //通知打印1的信号
    private Condition con1 = lock.newCondition();
    //通知打印2的信号
    private Condition con2 = lock.newCondition();
    //通知打印3的信号
    private Condition con3 = lock.newCondition();

    public void print1() {
        lock.lock();
        try {
            //本方法只能打印1，如果不是1就等待
            if (number != 1) {
                con1.await();//wait()
            }
            //如果是1，就打印“1”
            System.out.println(1);
            //打印完“1”之后，去唤醒打印“2”的线程
            number = 2;
            con2.signal();//notify;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();
        try {
            //本方法只能打印2，如果不是2就等待
            if (number != 2) {
                con2.await();
            }
            //如果是2，就打印“2”
            System.out.println(2);
            //打印完“2”之后，去唤醒打印“3”的线程
            number = 3;
            con3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print3() {
        lock.lock();

        try {
            //本方法只能打印3，如果不是3就等待
            if (number != 3) {
                con3.await();
            }
            //如果是3，就打印“3”
            System.out.println(3);
            //打印完“3”之后，去唤醒打印“1”的线程
            number = 1;
            con1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}