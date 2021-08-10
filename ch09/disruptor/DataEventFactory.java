package com.yanqun.disruptor.base;

import com.lmax.disruptor.EventFactory;

public class DataEventFactory implements EventFactory {
    //批量的产生 DataEvent对象
    @Override
    public Object newInstance() {
        return new DataEvent();
    }
}
