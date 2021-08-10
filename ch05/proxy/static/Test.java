package staticproxy;

public class Test {
	public static void main(String[] args) {
//		Subject realSubject =new RealSubject() ;
//		realSubject.rent();
		
		Subject proxySubject =new StaticProxy() ;
		proxySubject.rent();
		
	}
}
