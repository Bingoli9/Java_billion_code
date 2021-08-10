package rmi.test;

import rmi.server.RMIService;
import rmi.server.RMIServiceImpl;
import rmi.server.ServerCenter;
import rmi.server.ServerCenterImpl;

public class TestRMIServer {
	public static void main(String[] args) {
		//用线程的形式启动服务
		new Thread(new Runnable() {
			@Override
			public void run() {
				//服务中心
				ServerCenter server = new ServerCenterImpl(9999);
				//将RMIService接口及实现类，注册到服务中心
				server.register(RMIService.class, RMIServiceImpl.class);
				server.start(); 
			}
		}).start();
	}
}
