public class RealSubject2 implements Subject2 {
    @Override
    public void rentCar(String type) {
        System.out.println("租用的车类型:"+type);
    }
}