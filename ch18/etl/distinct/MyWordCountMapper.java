package mr.etl.distinct;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//数据清洗：去除重复数据
public class MyWordCountMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	//将每行数据，以key的形式输出到MR的下一个阶段
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException  {
		context.write(value, NullWritable.get());
	}
}
