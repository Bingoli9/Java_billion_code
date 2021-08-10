package observer1;

import java.util.Observable;
import java.util.Observer;


public class ConcreteObserver implements Observer {
    @Override
    public void update(Observable o, Object content) {
        System.out.println(content);
    }
}
