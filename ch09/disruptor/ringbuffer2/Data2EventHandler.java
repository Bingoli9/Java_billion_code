package ringbuffer2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Data2EventHandler implements WorkHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent event) throws Exception {
        //将data2设置一个四位随机数字
        event.setData2((int)(Math.random()*9000)+1000);
        System.out.println("Data2EventHandler:"+event);
    }
}