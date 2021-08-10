public class MyRPCServer implements MyRPCService {
	@Override
	publicboolean addStudent(String name, intage) {
		System.out.println("----模拟增加操作----");
		System.out.println("增加成功:" + name+","+age);
		returntrue;
	}
}