package rmi.server;
public interface ServerCenter {
	//启动服务
	public void start() ;
	//关闭服务
	public void stop();
	//注册服务
	public void register(Class service, Class serviceImpl);
}
