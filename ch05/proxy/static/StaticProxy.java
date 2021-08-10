package staticproxy;
//代理角色：执行真实角色的方法 + 一些其他操作...
public class StaticProxy  extends Subject{//Subject :  静态代理、     真实角色
	// 含有真实对象的引用(房主)
	Subject realSubject = new RealSubject() ;	
	
	
	
	public void before() {
		System.out.println("before:打扫.....");
	}
	public void after() {
		System.out.println("多收一个月租金...");
	}
	@Override
	public void rent() {
		this.before();
		//转租真实角色的房
				realSubject.rent(); 
		this.after(); 
	}
}