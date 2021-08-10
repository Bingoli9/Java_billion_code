public class ThreadDemo01 implements Runnable {
    //100张火车票
    private int tickets = 100;
    @Override
    public void run() {
       while (true) {
         sellTickets();//调用售票方法
       }
    }
   //售票方法
    public  void sellTickets() {
         if (tickets >0) {
 //打印线程名和剩余票数
             System.out.println(Thread.currentThread().getName() + tickets);
             tickets--;
         }
   }

   public static void main(String[] args) {
         ThreadDemo01 t = new ThreadDemo01();
        //创建两个线程并执行
         Thread t1 = new Thread(t);
         Thread t2 = new Thread(t);
         t1.setName("t1售票点站");
         t2.setName("t2售票点站");
         t1.start();
         t2.start();
   }
}