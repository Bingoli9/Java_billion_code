package decorator;

public class AISmartPhone extends SmartPhone {
    public AISmartPhone(Phone phone) {
        super(phone);
    }
    //给电话增加新的功能：人工智能
    public void aiFunction(){
        System.out.println("电话拥有人工智能的强大功能");
    }
    public void call(){
        super.call();
        aiFunction();
    }
}
