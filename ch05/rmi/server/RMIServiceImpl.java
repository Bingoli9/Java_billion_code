package rmi.server;
//服务端上，提供服务接口的实现类
public class RMIServiceImpl implements RMIService {
	@Override
	public String sayHi(String name) {
		return "hi,"+name ;
	}
}
