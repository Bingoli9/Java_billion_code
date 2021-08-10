package tollstation.record.group;
/*
 * map:输出
Key:bean（实际上 只使用了 “carPlate”字段） ,  bean(最大行驶时间、公里数)

告诉MR，key分组时  根据车牌第一个字段 分组


最终需要3个字段：
某个省份的车牌、	最大行驶时间、公里数


云, [bean,bean,bean,bean,bean]

				数据：	用mapreduce模拟	 	左外连接

连接：



1001      300	pd001    apple

map  reduce
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

//车辆的行驶信息
public class CarInfoBean implements WritableComparable<CarInfoBean> {
	// 行驶公里数
	private long distance;
	// 行驶时间
	private long minutes;
	// 行驶速度
	private double speed;
	
	private String carPlate ;

	//自定义排序：先根据省份排序，省份相同时再根据公里数降序
	public int compareTo(CarInfoBean bean) {
		 int result = getCarPlate().substring(0,1).compareTo( bean.getCarPlate().substring(0,1) );
		 if(result == 0)
			 result = -Long.compare(  this.getMinutes() , bean.getMinutes() );
		return  result;
	}
	
	public CarInfoBean() {
	}
	public CarInfoBean(long distance) {
		this.distance = distance;
	}
	public CarInfoBean(long distance, long minutes) {
		this.distance = distance;
		this.minutes = minutes;
	}
	public CarInfoBean(long distance, long minutes, double speed) {
		this.distance = distance;
		this.minutes = minutes;
		this.speed = speed;
	}
	
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public long getMinutes() {
		return minutes;
	}
	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	@Override
	public void write(DataOutput dataOutput) throws IOException {
		dataOutput.writeUTF(carPlate);
		dataOutput.writeLong(distance);
		dataOutput.writeLong(minutes);
		dataOutput.writeDouble(speed);
	}
	@Override
	public void readFields(DataInput dataInput) throws IOException {//对象流ObjectInputStream / ObjectOutputStream
		this.carPlate = dataInput.readUTF() ;
		this.distance = dataInput.readLong() ;
		this.minutes = dataInput.readLong();
		this.speed = dataInput.readDouble() ;
	}
	@Override
	public String toString() {
		return carPlate.substring(0, 1) +"\t"+carPlate+"\t"+ distance + "\t" + minutes + "\t" + speed;
	}
}
