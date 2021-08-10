package tollstation.record.group;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
//“欺骗”MR的类，必须继承自WritableComparator，并且重写其中的compare()方法
public class PrvoinceGroup  extends WritableComparator{
	//语法上，要将“欺骗”MR的元素类型放入super()中
	public PrvoinceGroup(){
		super(CarInfoBean.class,true) ;
	}
	//具体的“欺骗”策略：在判断两个CarInfoBean是否相同时，只判断CarInfoBean中carPlate属性的第一个字符（即车牌号上的“省份”）
	@Override
	public int compare(WritableComparable carType1, WritableComparable carType2) {
		CarInfoBean c1	= 	(CarInfoBean)carType1 ;
		CarInfoBean c2  = 	(CarInfoBean)carType2 ;
		String car1Province = c1.getCarPlate().substring(0,1) ;
		String car2Province = c2.getCarPlate().substring(0,1) ;
		return car1Province.compareTo(car2Province);
	}
}
