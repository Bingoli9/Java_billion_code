package decorator;
//装饰角色（Decorator）
public abstract class SmartPhone implements Phone {
    private Phone phone;
    public SmartPhone(Phone phone) {
        super();
        this.phone = phone;
    }
    @Override
    public void call() {
        phone.call();
    }
}
