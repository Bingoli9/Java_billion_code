package multi;


import com.lmax.disruptor.RingBuffer;


public class Producter {

	private final RingBuffer<DataEvent> ringBuffer;
	
	public Producter(RingBuffer<DataEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	public void product(String data){
		long sequence = ringBuffer.next();
		try {
			DataEvent order = ringBuffer.get(sequence);
			order.setData(data);
		} finally {
			ringBuffer.publish(sequence);
		}
	}
}
