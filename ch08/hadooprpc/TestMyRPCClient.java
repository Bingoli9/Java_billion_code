public class TestMyRPCClient {
	public static void main(String[] args) throws IOException {
		System.out.println("客户端启动...");
		//获取服务端的代理对象，其中第二个参数“1”就是服务接口中定义的versionID
		MyRPCService serviceProxy = RPC.getProxy(MyRPCService.class, 1
,new InetSocketAddress("127.0.0.1", 8888) , new Configuration());
		//调用服务端的addStudent()方法
		boolean result = serviceProxy.addStudent("zs", 23) ;
		if(result)
			System.out.println("增加成功！");
        RPC.stopProxy(serviceProxy);
	}
}