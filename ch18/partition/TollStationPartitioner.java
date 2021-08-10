package tollstation.record.partition;

import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class TollStationPartitioner extends Partitioner<Text, CarInfoBean>{
	public static HashMap<String, Integer> typeToPartition = new HashMap<String, Integer>();
	static{
		//规定不同车型存放的区
		typeToPartition.put("小型", 0);
		typeToPartition.put("中型", 1);
		typeToPartition.put("大型", 2);
		typeToPartition.put("特大型", 3);
	}
	/*
	 *  分区是在shuffle阶段执行的操作，因此map的输出值就是分区的输入值。
	 *  即，getPartition()拿到是数据格式如下：
	 *  		key				value
	 *  		车型				行驶公里数	行驶时间		行驶速度
	 *  例如           中型                         300			200		      90
	 */
	@Override
	public int getPartition(Text key, CarInfoBean bean, int numPartitions) {
		//车型
		String carType = key.toString();
		//根据车型分区
		Integer partitionId = typeToPartition.get(carType);//key->value
		return partitionId;//返回值：区号
	}
}
