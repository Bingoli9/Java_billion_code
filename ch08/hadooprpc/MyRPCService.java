public interface MyRPCService {
	//在Hadoop-RPC中必须定义versionID，否则会报“java.lang.NoSuchFieldException:versionID”异常
	longversionID = 1;
	boolean addStudent(String name,intage);
}