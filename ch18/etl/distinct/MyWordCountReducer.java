package mr.etl.distinct;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyWordCountReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
	@Override
	protected void reduce(Text key, Iterable<NullWritable> values, Context context)
			throws IOException, InterruptedException {
		//key:合并后的数据 ，也就是说 已经将重复的数据进行了合并（去重）  
		context.write(key, NullWritable.get());
	}
}
