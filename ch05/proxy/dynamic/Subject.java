package dynamicproxy;



//动态代理 的共同维护的 必须是一个 接口，不能是抽象类
public interface Subject {
	boolean  rent(int money);//租房
}
