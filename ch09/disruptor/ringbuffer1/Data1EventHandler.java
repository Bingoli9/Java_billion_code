package ringbuffer1;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

public class Data1EventHandler implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        //将data1设置为一个四位随机字符串
        event.setData1(UUID.randomUUID().toString().substring(0,4));
        System.out.println("Data1EventHandler:"+event);
    }
}