public class Test2 {
    public static void main(String[] args) {
        ...
        Subject2 realSubject2 = new RealSubject2();
        InvocationHandler handler2 = new DynamicProxy(realSubject2) ;
        Subject2 subProxy2 = (Subject2)Proxy.newProxyInstance(
              handler2.getClass().getClassLoader(),
                realSubject2.getClass().getInterfaces()  ,
                handler2) ;
        subProxy2.rentCar("宝马");
    }
}