package tollstation.record.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

//WritableComparable继承自 Writable, Comparable<T>
public class CarInfoBean implements WritableComparable<CarInfoBean>{
	//行驶公里数
	private long distance ;
	//行驶时间
	private long  minutes ;  
	//形式速度
	private double speed ;
	
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
	public void setBean(long distance, long minutes, double speed){
		this.distance = distance;
		this.minutes = minutes;
		this.speed = speed;
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
		dataOutput.writeLong(distance);
		dataOutput.writeLong(minutes);
		dataOutput.writeDouble(speed);
	}
	@Override
	public void readFields(DataInput dataInput) throws IOException {
		this.distance = dataInput.readLong() ;
		this.minutes = dataInput.readLong();
		this.speed = dataInput.readDouble() ;
	}
	//根据行驶时间升序
	public int compareTo(CarInfoBean bean) {
		return this.getMinutes() > bean.getMinutes() ? 1:-1 ; 
	}
	@Override
	public String toString() {
		return distance + "\t" + minutes + "\t" + speed;
	}

}
