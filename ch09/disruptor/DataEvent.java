package com.yanqun.disruptor.base;
//并发的数据类型
public class DataEvent {
    //本例仅仅包含一个char类型
    private char value;
    public char getValue() {
        return value;
    }
    public void setValue(char value) {
        this.value = value;
    }
}
