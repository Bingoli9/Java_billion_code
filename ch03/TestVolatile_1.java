public class TestVolatile_1 {
	public static volatile int num = 0;
	public static void main(String[] args) throws Exception {
		for (int i = 0; i <100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i <20000; i++) {
						 num++;//num++不是一个原子性操作
					}
				}
			}).start();
		}
		Thread.sleep(3000);//休眠3秒，确保创建的100个线程都已执行完毕
		System.out.println(num);
	 }
}