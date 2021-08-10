package multi;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<DataEvent>{
	
	private String cId;
	private static AtomicInteger count = new AtomicInteger(0);
	public Consumer(String cId){
		this.cId = cId;
	}

	@Override
	public void onEvent(DataEvent data) throws Exception {
		System.out.println("消费者 " + this.cId + "，消费数据：" + data.getData());
		count.incrementAndGet();
	}
	public int getCount(){
		return count.get();
	}
}
