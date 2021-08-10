package com.yanqun.disruptor.base;


import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
import java.util.Date;

public class DataEventProducerWithTranslator {
	private final RingBuffer<DataEvent> ringBuffer;
	public DataEventProducerWithTranslator(RingBuffer<DataEvent> ringBuffer)
	{
		this.ringBuffer = ringBuffer;
	}
	public void product(ByteBuffer value)
	{
		ringBuffer.publishEvent((event, sequence,buffer) -> event.setValue(buffer.getChar(0)), value);
	}
}
