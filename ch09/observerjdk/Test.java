package observer1;
public class Test {
    public static void main(String[] args) {
        ConcreteSubject xmPqy = new ConcreteSubject() ;
        ConcreteObserver ob1  = new ConcreteObserver() ;
        ConcreteObserver ob2  = new ConcreteObserver() ;
        ConcreteObserver ob3  = new ConcreteObserver() ;
        xmPqy.addObserver(ob1);
        xmPqy.addObserver(ob2);
        xmPqy.addObserver(ob3);

        xmPqy.notifyFriends("hello");

		xmPqy.deleteObserver(ob2);
		xmPqy.notifyFriends("world");
    }
}
