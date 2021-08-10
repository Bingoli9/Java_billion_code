package juc.swim;

import java.util.concurrent.DelayQueue;

public class Natatorium implements Runnable {
	// 用延迟队列模拟多个Swimmer，每个Swimmer的getDelay()方法就表示自己剩余的游泳时间
	private DelayQueue<Swimmer> queue = new DelayQueue<Swimmer>();

	// 标识游泳馆是否开业
	private volatile boolean isOpen = true;

	// 向DelayQueue中增加游泳者
	public void addSwimmer(String name, int playTime) {
		// 规定游泳的结束时间
		long endTime = System.currentTimeMillis() + playTime * 1000 * 60;
		Swimmer swimmer = new Swimmer(name, endTime);
		System.out.println(swimmer.getName() + "进入游泳馆，可供游泳时间：" + playTime + "分");
		this.queue.add(swimmer);
	}
	@Override
	public void run() {
		while (isOpen) {
			try {
				/*
				 * 注意：在DelayQueue中，take()并不会立刻取出元素。
				 * 只有当元素(Swimmer)所重写的getDelay()返回0或者负数时，才会真正取出该元素。
				 */
				Swimmer swimmer = queue.take();
				System.out.println(swimmer.getName() + "游泳时间结束");
				// 如果DelayQueue中的元素已被取完，则停止线程
				if (queue.size() == 0) {
					isOpen = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}