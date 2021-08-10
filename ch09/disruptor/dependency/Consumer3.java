package dependency;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
//消费者3
public class Consumer3 implements  EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setData3("c3");
    }
}