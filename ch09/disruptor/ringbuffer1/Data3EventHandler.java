package ringbuffer1;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

public class Data3EventHandler implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        //将data3设置为一个1000以内的随机小数
        event.setData3(Math.random()*1000);
        System.out.println("Data3EventHandler:"+event);
    }
}