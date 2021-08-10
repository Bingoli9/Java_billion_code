package lock.print;

import java.util.concurrent.Semaphore;

public class LoopPrint123WithSemaphore {
    public static void main(String[] args) {
        Print123WithSemaphore pring = new Print123WithSemaphore();
        new Thread(() -> pring.print1()).start();
        new Thread(() -> pring.pring2()).start();
        new Thread(() -> pring.print3()).start();
    }
}

class Print123WithSemaphore {
    //定义三个信号量，并且这三个信号量一共只有1个许可证
    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);

    public void print1() {
        print("1", semaphore1, semaphore2);
    }
    public void pring2() {
        print("2", semaphore2, semaphore3);
    }
    public void print3() {
        print("3", semaphore3, semaphore1);
    }

    /*
        value：打印的字符
        currentSemaphore：当前信号量
        nextSemaphore：下一个信号量
        每次个线程执行时，先获取唯一的许可，继而打印自己的value值；之后再将许可释放给下一个信号量，让下一个信号量打印value……
     */
    private void print(String value, Semaphore currentSemaphore, Semaphore nextSemaphore) {
        for (int i = 0; i < 10; ) {
            try {
                currentSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() +" print "+ value);
                Thread.sleep(1000);
                i++;
                nextSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}