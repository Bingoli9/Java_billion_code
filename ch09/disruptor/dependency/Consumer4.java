package dependency;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
//消费者4
public class Consumer4 implements EventHandler<DataEvent> {
	  
    @Override  
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setData4("c4");
    }  
}