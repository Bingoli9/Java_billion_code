package mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyWordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	/*
	 * reduce拿到的是shuffle处理后的数据，数据形式为“单词,[1,1,...,1]”；
	 * reduce要将这些数据，处理成“单词，出现次数”的形式，如“B,3”表示统计结果中有3个B。
	 */
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int count = 0;
		for (IntWritable value : values) {
			// value.get()可以获取数组[1,1,...,1]中的每一个元素值
			count += value.get();
		}		//hello\t3
		context.write(key, new IntWritable(count));
	}
}
