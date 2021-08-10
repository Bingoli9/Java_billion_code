package observer;

//抽象主题
public interface Subject {
	//增加观察者
	void addObserver(Observer observer);
	//删除观察者
	void deleteObserver(Observer observer);
	//通知所有观察者
	void notifyObservers(String content);
}
