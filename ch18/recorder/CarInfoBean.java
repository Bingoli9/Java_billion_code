package tollstation.record;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

//车辆的行驶信息
public class CarInfoBean implements Writable {
	// 行驶公里数
	private long distance;
	// 行驶时间
	private long minutes;
	// 行驶速度
	private double speed;

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
	public void readFields(DataInput dataInput) throws IOException {//对象流ObjectInputStream / ObjectOutputStream
		this.distance = dataInput.readLong() ;
		this.minutes = dataInput.readLong();
		this.speed = dataInput.readDouble() ;
	}
	@Override
	public String toString() {
		return distance + "\t" + minutes + "\t" + speed;
	}

}
