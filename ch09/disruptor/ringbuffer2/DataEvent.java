package ringbuffer2;
public class DataEvent {
    private String data0;
    private String data1;
    private int data2;
    private double data3;

    public String getData0() {
        return data0;
    }

    public void setData0(String data0) {
        this.data0 = data0;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public int getData2() {
        return data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public double getData3() {
        return data3;
    }

    public void setData3(double data3) {
        this.data3 = data3;
    }

    @Override
    public String toString() {
        return "DataEvent{" +
                "data0='" + data0 + '\'' +
                ", data1='" + data1 + '\'' +
                ", data2=" + data2 +
                ", data3=" + data3 +
                '}';
    }
}
