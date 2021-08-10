package tollstation.record.join;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

//车辆的行驶信息，拥有tollstation.txt和cartype.txt的全部字段
public class CarInfoBean implements Writable {
	//tollstation.txt中的字段
	private String startTime ; 
	private String endTime ; 
	private long distance;
	private int carTypeId ;
	private String carPlate ;
	//cartype.txt中的字段
	private String typeName ; 
	private float pl ;
	//0：行驶信息 ，1：车型信息
	private int flag  ;
	
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public int getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public float getPl() {
		return pl;
	}
	public void setPl(float pl) {
		this.pl = pl;
	}
	
	//设置行驶属性
	public void setTollStationBean(String startTime, String endTime, long distance, String carPlate) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.distance = distance;
			this.carPlate = carPlate;
		}
	
	//设置车型属性
	public void setCarTypeBean(String typeName,float pl) {
			this.typeName = typeName;
			this.pl = pl;
	}
	
	//设置全部属性
	public void setBean(String startTime, String endTime, long distance, String carPlate, String typeName,float pl,int flag) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.distance = distance;
		this.carPlate = carPlate;
		this.typeName = typeName;
		this.pl = pl;
		this.flag = flag; 
	}
	@Override
	public void write(DataOutput dataOutput) throws IOException {
		dataOutput.writeUTF(startTime);
		dataOutput.writeUTF(endTime);
		dataOutput.writeLong(distance);
		dataOutput.writeUTF(carPlate);
		dataOutput.writeUTF(typeName);
		dataOutput.writeFloat(pl);
		dataOutput.writeInt(flag);
	}
	@Override
	public void readFields(DataInput dataInput) throws IOException {
		this.startTime = dataInput.readUTF();
		this.endTime = dataInput.readUTF();
		this.distance = dataInput.readLong() ;
		this.carPlate = dataInput.readUTF() ;
		this.typeName = dataInput.readUTF() ;
		this.pl = dataInput.readFloat() ;
		this.flag = dataInput.readInt() ;
	}
	@Override
	public String toString() {
		return startTime + "\t" + endTime + "\t" + distance	+ "\t"
				+ carPlate + "\t" + typeName + "\t" + pl ;
	}
}
