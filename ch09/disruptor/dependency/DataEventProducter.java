package dependency;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

//生产者
public class DataEventProducter implements Runnable {
    Disruptor<DataEvent> disruptor;
    private CountDownLatch latch;

    public DataEventProducter(CountDownLatch latch, Disruptor<DataEvent> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        OrderEventTranslator orderEventTranslator = new OrderEventTranslator();
        disruptor.publishEvent(orderEventTranslator);
        latch.countDown();
    }
}

class OrderEventTranslator implements EventTranslator<DataEvent> {
    @Override
    public void translateTo(DataEvent event, long sequence) {
        this.generateTrade(event);
    }
    //生产者处理data0
    private DataEvent generateTrade(DataEvent dataEvent) {
        dataEvent.setData0("data0");
        return dataEvent;
    }
}