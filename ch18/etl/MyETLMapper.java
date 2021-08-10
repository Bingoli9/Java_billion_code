package mr.etl;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyETLMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	enum MyCount{ERROR,NORMAL}
	//清晰数据不全的脏数据
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException  {
		String line = value.toString() ;
		//对每行数据进行拆分，根据拆分后的个数判断是否为脏数据 （正常情况下，每行数据应该有5个字段）
		String[] values = line.split("\t") ;
		if(values.length ==5){
			context.write(value,NullWritable.get());
			context.getCounter(MyCount.NORMAL).increment(1);
		}else{
			//记录数据不全的个数
			context.getCounter(MyCount.ERROR).increment(1);
			return ;
		}
	}
}
