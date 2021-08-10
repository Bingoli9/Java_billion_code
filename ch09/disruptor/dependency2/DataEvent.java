package dependency2;

public class DataEvent {
	//生产者处理：data0
	private String data0;
	//消费者处理：data1-data3
	private String data1;
	private String data2;
	private String data3;

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

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

    @Override
    public String toString() {
        return "DataEvent{" +
                "data0='" + data0 + '\'' +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", data3='" + data3 + '\'' +
                '}';
    }
}