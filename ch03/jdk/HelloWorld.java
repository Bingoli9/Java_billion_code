package lambda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HelloWorld {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        }).start();
        /*  用Lambda的等价写法：
            new Thread( () ->System.out.println("Hello World") ) .start();
         */
    }
}

