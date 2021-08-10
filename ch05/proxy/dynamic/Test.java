package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
	    //真实对象
		Subject realSubject = new RealSubject();
		//初步的代理对象:handler就是真实角色realSubject的初步代理对象
		InvocationHandler handler = new DynamicProxy(realSubject) ;
		
		//最终的代理对象
		/*
		 * newProxyInstance(a,b,c) ;
		 * a:初步代理对象的类加载器
		 * b:接口类型的数组。要代理的方法是在哪些接口中定义的，即realSubject的接口；因为语法上允许一个类实现多个接口，因此接口是一个数组
		 * c:要将哪一个 初步代理对象 转成最终代理对象
		 */
		Subject subProxy = (Subject)Proxy.newProxyInstance(
		        handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces()  ,
                handler) ;
		boolean result = subProxy.rent(3000);//会调用动态代理对象的invoke()
        System.out.println(subProxy.getClass().getName());
        System.out.println(result?"ok":"error");

	}
}
