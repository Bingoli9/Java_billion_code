package rmi.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//服务注册中心的具体实现
public class ServerCenterImpl implements ServerCenter {
    // map：服务端的所有可供客户端访问的接口，都注册到该map中
    // key:接口的名字（如"RMIService"），value:真正的提供服务的类（如RMIServiceImpl类）
    private static HashMap<String, Class> serviceRegiser = new HashMap<>();
    //服务端的端口号
    private static int port;
    //线程池：连接池中存在多个线程对象，每个线程对象都可以处理一个客户请求
    private static ExecutorService executor
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //是否开启服务
    private static volatile boolean isRunning = false;

    public ServerCenterImpl(int port) {
        this.port = port;
    }

    // 开启服务端服务
    @Override
    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(port));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        isRunning = true;
        //客户端每次请求一次请求，服务端从线程池中启动一个线程对象去处理
        while (true) {
            //具体的服务内容：接收客户端请求，处理请求，并返回结果
            System.out.println("服务已启动...");
            Socket socket = null;
            try {
                socket = server.accept();// 等待客户端连接
            } catch (IOException e) {
                e.printStackTrace();
            }
            //启动一个线程 去处理客户请求
            executor.execute(new ServiceTask(socket));
        }
    }

    //关闭服务
    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    //将接口名和接口实现类一一对应，以便于接收到请求时，能及时获取到对应的服务实现类
    @Override
    public void register(Class service, Class serviceImpl) {
        serviceRegiser.put(service.getName(), serviceImpl);
    }

    //处理请求的线程
    private static class ServiceTask implements Runnable {
        private Socket socket;

        public ServiceTask() {
        }

        public ServiceTask(Socket socket) {
            this.socket = socket;
        }

        //具体的处理逻辑
        @Override
        public void run() {
            ObjectOutputStream output = null;
            ObjectInputStream input = null;
            try {
                //接收到客户端的各个请求参数（接口名、方法名、参数类型、参数值）
                input = new ObjectInputStream(socket.getInputStream());
                // 因为ObjectInputStream对发送数据的顺序有严格要求，因此必须按照发送的顺序逐个接收
                // 请求的接口名
                String serviceName = input.readUTF();
                // 请求的方法名
                String methodName = input.readUTF();
                // 请求方法的参数类型
                Class[] parameterTypes = (Class[]) input.readObject();
                // 请求方法的参数名
                Object[] arguments = (Object[]) input.readObject();
                // 根据客户请求，到服务注册中心map中找到与之对应的具体接口（即RMIService）
                Class ServiceClass = serviceRegiser.get(serviceName);
                // 构建请求的方法
                Method method = ServiceClass.getMethod(methodName, parameterTypes);
                // 执行该方法
                Object result = method.invoke(ServiceClass.newInstance(), arguments);
                // 将执行完毕的返回值，返回给客户端
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }


}
