package dependency;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
//消费者5
public class Consumer5 implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setData5("c5");
    }
}