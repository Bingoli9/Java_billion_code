package lock.print;

public class TestLoopPrint123 {
    public static void main(String[] args) {
        LoopPrint123 loopPrint = new LoopPrint123();
        //创建一个线程，用于不断的打印1(调用print1()方法)
        new Thread(() -> {
            while (true) {
                loopPrint.print1();
            }
        }).start();
        //创建一个线程，用于不断的打印2(调用print2()方法)
        new Thread(() -> {
            while (true) {
                loopPrint.print2();
            }
        }).start();

        //创建一个线程，用于不断的打印3(调用print3()方法)
        new Thread(() -> {
            while (true) {
                loopPrint.print3();
            }
        }).start();
    }
}
