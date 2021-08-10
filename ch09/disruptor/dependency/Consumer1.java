package dependency;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

//消费者1
public class Consumer1 implements EventHandler<DataEvent>,WorkHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }
    @Override
    public void onEvent(DataEvent event) throws Exception {
    	event.setData1("c1");
    }
}