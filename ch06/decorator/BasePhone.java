package decorator;
//具体构件角色（ConcreteComponent）
public class BasePhone implements Phone {
    @Override
    public void call() {
        System.out.println("打电话");
    }
}
