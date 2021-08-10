package decorator;

public class AutoSizeSmartPhone extends SmartPhone {
    public AutoSizeSmartPhone(Phone phone) {
        super(phone);
    }
    //给电话增加新的功能：手机尺寸自动伸缩
    public void autoSize(){
        System.out.println("手机的尺寸可以自动伸缩");
    }
    public void call(){
        super.call();
        autoSize();
    }
}
