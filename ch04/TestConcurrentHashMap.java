package juc;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();
        chm.put("key1", "value1");
        chm.put("key2", "value2");
        chm.put("key3", "value3");
        chm.putIfAbsent("key3", "value3");//如果key已存在，则不再增加
        chm.putIfAbsent("key4", "value4");//如果key不存在，则增加
        System.out.println(chm);
    }
}
