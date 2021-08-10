package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestInterruptibly {
	private Lock myLock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		TestInterruptibly myInter = new TestInterruptibly();
		MyThread aThread = new MyThread(myInter);
		aThread.setName("A");
		MyThread bThread = new MyThread(myInter);
		bThread.setName("B");
		aThread.start();
		bThread.start();
		Thread.sleep(1000);
		/*
		 * main线程休眠1秒，A、B线程各休眠5秒； 即当main结束休眠时，A、B仍然在休眠； 因此,main线程会中断B线程的等待
		 */
		bThread.interrupt();
	}

	public void myInterrupt(Thread thread) throws InterruptedException {
		myLock.lockInterruptibly();
		try {
			System.out.println(thread.getName() + "-加锁");
			Thread.sleep(3000);
		} finally {
			System.out.println(thread.getName() + "-解锁");
			myLock.unlock();
		}
	}
}

class MyThread extends Thread {
	private TestInterruptibly myInter = null;

	public MyThread(TestInterruptibly myInter) {
		this.myInter = myInter;
	}

	@Override
	public void run() {
		try {
			myInter.myInterrupt(Thread.currentThread());
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + "被中断");
		}
	}
}
