class CarStock {
   int cars;
   private Lock lock = new ReentrantLock();
   private Condition condition = lock.newCondition();

   //生产车
   public void productCar() {
      lock.lock();
      try {
         if (cars <20) {
            System.out.println("生产车...."+ cars);
            cars++;
            condition.signalAll();//唤醒
         } else {
                condition.await();
         }
      } catch (..) {...} 
      finally {
            lock.unlock();
      }
    }
//消费车
    public void resumeCar() {
        lock.lock();
        try {
            if (cars >0) {
                System.out.println("销售车...."+ cars);
                cars--;
                condition.signalAll();//唤醒
            } else {
                condition.await();//等待
            }
        } catch (...) {...} 
        finally {
            lock.unlock();
        }
    }
}