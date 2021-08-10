package staticproxy;
//真实角色...
public class RealSubject extends Subject {

	@Override
	public void rent() {
		
		System.out.println("真正的租房...");

	}

}
