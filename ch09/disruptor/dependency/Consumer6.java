package dependency;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
//消费者6（最后一个消费者）
public class Consumer6 implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setData6("c6");
        System.out.println(event);
    }
}  