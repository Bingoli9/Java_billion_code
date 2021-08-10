package juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
 * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。并发迭代操作多时可以选择。
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        /*
            java.util.ArrayList
            java.util.Arrays$ArrayList :该类中，并没有提供add()方法。如果调用add()，则会抛异常：UnsupportedOperationException
         */
        CopyOnWriteArrayList<String> names =  new   CopyOnWriteArrayList<String>();
//        ArrayList<String> names =  new ArrayList<>();
//        Vector<String> names = new Vector<>() ;//1.5
        names.add("zs") ;
        names.add("ls") ;
        names.add("ww") ;
        Iterator<String> iter = names.iterator();
        while(iter.hasNext()) {
             System.out.println(iter.next());//仅仅对集合进行 读操作，不会有异常
             names.add("x");//仅仅对集合进行 写操作：因为ArrayList会动态扩容（1.5倍），因此names会无限扩大，因此会包
        }
    }
}
