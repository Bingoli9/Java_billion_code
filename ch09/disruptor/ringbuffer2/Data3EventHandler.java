package ringbuffer2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Data3EventHandler implements WorkHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event) throws Exception {
        //将data3设置为一个1000以内的随机小数
        event.setData3(Math.random()*1000);
        System.out.println("Data3EventHandler:"+event);
    }
}