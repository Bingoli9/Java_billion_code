package mr.myoutput;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyTollStation {
	static class TollStationMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
		//输入：    key：偏移量    value:一行的内容
		@Override
		protected void map(LongWritable key, Text value, Context context){
			try {
				//输出： key:一行内容   value:null
				context.write(value, NullWritable.get());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MyTollStation.class);
		job.setMapperClass(TollStationMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		//将OutputFormat制定为自定义输出类
		job.setOutputFormatClass(MyOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
	}
}
