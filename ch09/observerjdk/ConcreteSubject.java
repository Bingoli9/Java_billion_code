package observer1;

import java.util.Observable;

public class ConcreteSubject  extends Observable{
    public void notifyFriends(Object msg) {
        //标识主题发生了改变
        setChanged();
        //通知所有的观察者
        notifyObservers(msg) ;
    }
}
