package rmi.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RMIClient {
    /*获取代表服务端接口的动态代理对象（RMIService）
    serviceInterface:请求的接口名
    addr:待请求服务端的ip:端口
    */
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceInterface,
                                          InetSocketAddress addr) {

		/*
		   newProxyInstance(a,b,c)中,前两个参数的含义如下：
		   	a:类加载器 ：需要代理那个对象的类加载器
		    b:用于表示需要代理的对象提供了哪些方法。Java是单继承、多实现，
		    	因此，如果某一个对象实现了多个接口，那么该对象就拥有其全部接口的所有方法，因此是一个接口数组。
		        例如，如果有A implements B接口,c接口，并且B接口中有3个方法，C接口中有2个方法，那么A的对象就拥有5个方法（暂不考虑A自身提供的方法）
		 */
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}, new InvocationHandler() {

                    //proxy:代理的对象, method：哪个方法（sayHi(参数列表)）, args:参数列表
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        //客户端向服务端发送请求：请求某一个具体的接口
                        Socket socket = new Socket();
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        try {
                            //addr包含了要访问的服务端的Ip和端口
                            socket.connect(addr);
                            //通过序列化流（对象流）向服务端发送请求
                            output = new ObjectOutputStream(socket.getOutputStream());
                            //发送请求的接口名
                            output.writeUTF(serviceInterface.getName());
                            //发送请求的方法名
                            output.writeUTF(method.getName());
                            //发送请求的方法的参数的类型
                            output.writeObject(method.getParameterTypes());
                            //发送请求的方法的参数值
                            output.writeObject(args);
                            //等待服务端处理...
                            //接收服务端处理后的返回值
                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        } finally {
                            try {
                                if (output != null) output.close();
                                if (input != null) input.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
