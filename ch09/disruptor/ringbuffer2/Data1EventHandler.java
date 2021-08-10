package ringbuffer2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

public class Data1EventHandler implements WorkHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event) throws Exception {
        event.setData1(UUID.randomUUID().toString().substring(0,4));
        System.out.println("Data1EventHandler:"+event);
    }
}