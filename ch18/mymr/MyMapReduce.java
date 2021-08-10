package mr.mymr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class MyMapReduce extends Configured  implements Tool {
	static class MyMapper extends Mapper<NullWritable, Text, Text, NullWritable>{
		//处理输入的文件，并以“key=文件内容，value=null”的形式输出
		@Override
		protected void map(NullWritable key, Text value,Context context)
				throws IOException, InterruptedException {
			context.write(value,NullWritable.get() );
		}
	}
	@Override
	public Configuration getConf() {
		return super.getConf();
	}
	@Override
	public void setConf(Configuration conf) {
		super.setConf(conf);
	}
	
	//通过设置Tool接口中的run()方法设置Job参数,而不用再在main()中设置
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration() ;
		Job job = Job.getInstance(conf);
		job.setJarByClass(MyMapReduce.class);
		job.setMapperClass(MyMapper.class);
		//指定输入方式为自定义的MyInputFormat
		job.setInputFormatClass(MyInputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		return job.waitForCompletion(true) ? 0:1;
	}
	public static void main(String[] args) throws Exception {
		ToolRunner.run(  new MyMapReduce(), args) ;
	}
}
