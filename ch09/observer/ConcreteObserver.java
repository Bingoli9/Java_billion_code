package observer;

public class ConcreteObserver  implements Observer{
    //更新观察到的内容
	@Override
	public void update(String content) {
	    System.out.println(content);
	}
}