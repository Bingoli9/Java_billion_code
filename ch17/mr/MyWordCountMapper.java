package mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyWordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	enum MyCount{ERROR,NORMAL}
	
	// 将读取到的每行数据，以空格为分隔符进行了拆分，并输出成 "单词,1"的形式
	@Override
	protected void map(LongWritable key, Text value, Context context)  {
		//context:获取文件名
		//context:计数器   
		String line = value.toString();
		String[] words = line.split(" ");
		try{
			for (String word : words) {
				context.write(new Text(word), new IntWritable(1));// key,v
			}
		}catch(Exception e){
			e.printStackTrace();
			context.getCounter(MyCount.NORMAL).increment(1);
			context.getCounter("NORMALGroup", "NORMAL").increment(1);
		}
		context.getCounter(MyCount.ERROR).increment(1);
		context.getCounter("ERRORGroup", "ERROR").increment(1);
	}
}
