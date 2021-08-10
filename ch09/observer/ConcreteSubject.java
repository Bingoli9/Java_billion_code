package observer;

import java.util.ArrayList;
import java.util.List;

//具体主题
public class ConcreteSubject implements Subject {
    //观察此主题的所有观察者，即小明的所有好友
    private List<Observer> observers = new ArrayList<Observer>();

    //增加观察者
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    //删除观察者
    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    //通知观察者
    @Override
    public void notifyObservers(String content) {
        for (Observer observer : observers) {
            //通知每个观察者：更新数据
            observer.update(content);
        }
    }
}
