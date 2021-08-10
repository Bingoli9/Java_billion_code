package observer;

//抽象观察者
public interface Observer {
	//收到主题的通知后，更新自己
	void update(String content);
}
