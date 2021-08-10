public class TestMyRPCServer {
	public static void main(String[] args) {
		Server server;
		try {
			server = new RPC.Builder(new Configuration())        
				        .setProtocol(MyRPCService.class)//提供的服务
				        .setInstance(new MyRPCServer())//创建实例对象
				        .setBindAddress("127.0.0.1")//绑定服务端的IP地址
				        .setPort(8888)//绑定服务端的端口号
				        .build(); 
			System.out.println("服务端启动...");
		    server.start();
		} catch (HadoopIllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}
}