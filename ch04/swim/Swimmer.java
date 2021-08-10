package juc.swim;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Swimmer implements Delayed {

	private String name;
	private long endTime;

	public Swimmer() {
	}

	public Swimmer(String name, long endTime) {
		this.name = name;
		this.endTime = endTime;
	}

	public String getName() {
		return this.name;
	}

	/*
	   是否还有剩余时间。
	   如果返回正数，表明还有剩余时间；
	   如果返回0或者负数，说明已超时；超时时，才会让DelayQueue的take()方法真正取出元素。
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return endTime - System.currentTimeMillis();
	}

	//线程（游泳者）之间,根据剩余时间的大小进行排序
	@Override
	public int compareTo(Delayed delayed) {
		Swimmer swimmer = (Swimmer) delayed;
		return this.getDelay(TimeUnit.SECONDS) - swimmer.getDelay(TimeUnit.SECONDS) > 0 ? 1 : 0;
	}

}