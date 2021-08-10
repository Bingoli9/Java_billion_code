public class TestVolatile_2 {
	public static AtomicInteger  num = new AtomicInteger(0);
	public static void main(String[] args) throws Exception {
	   for (int i = 0; i <100; i++) {
		  new Thread(new Runnable() {
			 @Override
			 public void run() {
				for (int i = 0; i <20000; i++) {
				  num.incrementAndGet() ;// num自增，功能上相当于int类型的num++操作
				}
			 }
		   }).start();
		}
	   Thread.sleep(3000);//休眠3秒，确保创建的100个线程都已执行完毕
	   System.out.println(num);
	}
}