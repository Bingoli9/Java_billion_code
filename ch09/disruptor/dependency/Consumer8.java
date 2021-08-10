package dependency;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
//消费者8
public class Consumer8 implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setData8("c8");
    }
}  