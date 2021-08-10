package mr.common.major.second;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SchoolCommonMajorsMapper extends Mapper<LongWritable, Text, Text, Text> {
	Text k = new Text();
	Text v = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] infos = line.split("\t");
		// 有共同专业的2各学校
		String twoShools = infos[0];
		// 共同的专业
		String major = infos[1];
		k.set(twoShools);
		v.set(major);
		context.write(k, v);
	}
}
