package rmi.test;
import rmi.client.RMIClient;
import rmi.server.RMIService;
import java.net.InetSocketAddress;
//先启动服务端，再启动客户端，就能看到客户端成功的调用了服务端上的sayHi()方法。
public class TestRMIClient {
    public static void main(String[] args) throws ClassNotFoundException {
        //调用远程的rmi.server.RMIService接口，并执行接口中的sayHi()方法
        RMIService service = RMIClient.getRemoteProxyObj(
                Class.forName("rmi.server.RMIService" ) ,
                new InetSocketAddress("127.0.0.1", 9999)) ;
        System.out.println( service.sayHi("zs")  ) ;
    }
}
