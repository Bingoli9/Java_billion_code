package dynamicproxy;

//真实角色
public class RealSubject implements Subject {
	@Override
	public boolean rent(int money)
    {
		System.out.println("租房"+money+"元");
		return true ;
	}

}
