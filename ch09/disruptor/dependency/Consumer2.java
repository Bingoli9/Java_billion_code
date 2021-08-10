package dependency;


import com.lmax.disruptor.EventHandler;
//消费者2
public class Consumer2 implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setData2("c2");
    }  
}