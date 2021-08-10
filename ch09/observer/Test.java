package observer;

import javax.security.auth.Subject;
import java.util.Observer;


public class Test {
    public static void main(String[] args) {
        //主题：小明的朋友圈
        ConcreteSubject xmPyq = new ConcreteSubject();
        //三个观察者
        ConcreteObserver observerZs = new ConcreteObserver();
        ConcreteObserver observerLs = new ConcreteObserver();
        ConcreteObserver observerWw = new ConcreteObserver();
        //给主题增加三个观察者
        xmPyq.addObserver(observerZs);
        xmPyq.addObserver(observerLs);
        xmPyq.addObserver(observerWw);
        //主题发生改变，并通知观察者
        xmPyq.notifyObservers("天气不错...");
        //删除第二个观察者
        xmPyq.deleteObserver(observerLs);
        xmPyq.notifyObservers("饿了...");
    }
}
