package lock;
public class DeadLock {
    public static String resource1 = "resource1";
    public static String resourcd2 = "resourcd2";
    public static void main(String[] args){
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }    
}
class Lock1 implements Runnable{
    @Override
    public void run(){
        try{
            synchronized(DeadLock.resource1){
                System.out.println("线程1-加锁了resource1，正在等待resource2");
                synchronized(DeadLock.resourcd2){
                    System.out.println("线程1-等待使用resource2...");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Lock2 implements Runnable{
    @Override
    public void run(){
        try{
            synchronized(DeadLock.resourcd2){
                System.out.println("线程2-加锁了resource2，正在等待resource1");
                synchronized(DeadLock.resource1){
                    System.out.println("线程2-等待使用resource1...");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}