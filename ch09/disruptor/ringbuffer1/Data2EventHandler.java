package ringbuffer1;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

public class Data2EventHandler implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        //将data2设置一个四位随机数字
        event.setData2((int)(Math.random()*9000)+1000);
        System.out.println("Data2EventHandler:"+event);
    }
}