package juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TestPriorityBlockingQueue {

	public static void main(String[] args) throws Exception{
		//通过构造方法，传入了一个实现了Comparable接口的MyJob类，在MyJob类中通过重写compareTo()定义了优先级规则
		BlockingQueue<MyJob> priorityQueue = new PriorityBlockingQueue<MyJob>();
		priorityQueue.add(new MyJob(3));	
		priorityQueue.add(new MyJob(2));	
		priorityQueue.add(new MyJob(1));  
		//优先级的排序规则，会在第一次调用take()方法之后生效
		System.out.println("队列：" + priorityQueue);//默认队中的顺序是3，2，1
		System.out.println("取出队列中的一个元素：" +priorityQueue.take().getId());//排序后，队中的顺序是1，2，3
		System.out.println("容器：" + priorityQueue);
		
	}
}
