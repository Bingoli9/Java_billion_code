package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class DynamicProxy  implements InvocationHandler{
	private  Object obj ;//可以代理任意 类型的角色
	public DynamicProxy(Object obj ) {
		this.obj = obj ;
	}

	public void before() {
		System.out.println("before rent()...");
	}
	/*
	 * proxy:代理的角色
	 * method:被代理的方法 rent()
	 * args：方法的参数
	 * return返回值：真正被代理的方法rent()的返回值
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.before();
//        System.out.println(proxy); //死循环,因此proxy会自动调用invoke()本身
		//动态代理 要想执行rent()，必须调用 真实角色的rent();
                            //person.sleep(time);
                            //sleep.invoke(person,time);
		boolean result = (boolean)(method.invoke(obj, args)) ;//通过反射调用rent()方法
		//对象.方法(xx);
		this.after();
		return result;
	}

	public void after() {
	    System.out.println("after...");
	}
	
	
}
