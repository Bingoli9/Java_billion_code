package decorator;

public class Test {
    public static void main(String[] args) {
        //基础的电话功能
        System.out.println("--基础的电话功能--");
        BasePhone basePhone =new BasePhone();
        basePhone.call();
        //进行AISmartPhone装饰后的电话
        System.out.println("\n--智能手机：增加了AI的功能--");
        AISmartPhone AISmartPhone =new AISmartPhone(basePhone);
        AISmartPhone.call();
        //进行AutoSizeSmartPhone装饰后的电话
        System.out.println("\n--智能手机：增加了自动伸缩的功能--");
        AutoSizeSmartPhone autoSizeSmartPhone =new AutoSizeSmartPhone(basePhone);
        autoSizeSmartPhone.call();

        //进行AISmartPhone及AutoSizeSmartPhone两次装饰后的电话
        System.out.println("\n--智能手机：增加了AI和自动伸缩的功能--");
        SmartPhone smartPhone =new AutoSizeSmartPhone(new AISmartPhone(basePhone));
        smartPhone.call();
    }
}