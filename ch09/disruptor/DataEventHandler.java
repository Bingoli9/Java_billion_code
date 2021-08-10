package com.yanqun.disruptor.base;

import com.lmax.disruptor.EventHandler;

/**
 * @author yanqun
 * @create 2018/8/27  10:26
 */
public class DataEventHandler implements EventHandler<DataEvent> {
    //当生产者将一个event，发布到RingBuffer时，触发此方法；者消费可以通过此方法消费生产者在ringBuffer中的产物。
    @Override
    public void onEvent(DataEvent dataEvent, long l, boolean b) throws Exception {
        System.out.println("消费：" + dataEvent.getValue());//消费ringBuffer中的产物
    }
}
