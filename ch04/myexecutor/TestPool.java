package myexecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;


public class TestPool {
	public static void main(String[] args) throws Exception {
		Future<String> result = null;
		ScheduledExecutorService schedulPool = Executors.newScheduledThreadPool(4);
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 2; i++) {
			/*
			 * schedule(a,b,c)三个参数的含义：
			 * a:向线程池中提交的任务；
			 * b:该任务等待多长时间之后，才会被执行
			 * c:b的时间单位
			 */
			result = schedulPool.schedule(new ThreadTask("thread"+i), (int)(Math.random()*10), TimeUnit.SECONDS);
			//存储各个线程的执行结果
			results.add(result);
		}
		//打印结果
		for(Future<String> res: results){
			System.out.println(res.isDone() ? "已完成":"未完成");
			System.out.println("等待线程执行完毕后，返回的结果： " + res.get());
		}
		schedulPool.shutdown();
	}
}
