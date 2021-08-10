package mr.etl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import mr.etl.MyETLMapper.MyCount;

public class MyETLDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf);

		// MapReduce会根据传入的class参数找到job依赖的jar包
		job.setJarByClass(MyETLDriver.class);

		// 指定本程序的Map、Reduce类
		job.setMapperClass(MyETLMapper.class);
		job.setNumReduceTasks(0);

		// 指定输出数据的key/value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// 指定需要处理文件的hdfs路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));// a.txt
		// 在MapReduce计算完毕后，将结果输出到hdfs的文件路径
		FileOutputFormat.setOutputPath(job, new Path(args[1]));// b.txt

		// 向MapReduce提交任务
		boolean result = job.waitForCompletion(true);
		System.out.println(job.getCounters().findCounter(MyCount.ERROR).getValue());
		System.out.println(job.getCounters().findCounter(MyCount.NORMAL).getValue());
		System.exit(result ? 0 : 1);
	}
}
