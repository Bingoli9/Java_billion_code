package mr.etl.distinct;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

//设置MapReduce程序的各种参数，最后以job的形式提交到MapReduce中执行
public class MyWordCountDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);
		
		//MapReduce会根据传入的class参数找到job依赖的jar包
		job.setJarByClass(MyWordCountDriver.class);
		
		//指定本程序的Map、Reduce类
		job.setMapperClass(MyWordCountMapper.class);
		job.setReducerClass(MyWordCountReducer.class);//全局求和
	
		//指定map输出数据的key/value类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		//指定reduce输出数据的key/value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		//指定需要处理文件的hdfs路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));//a.txt
		//在MapReduce计算完毕后，将结果输出到hdfs的文件路径
		FileOutputFormat.setOutputPath(job, new Path(args[1]));//b.txt
		
		//向MapReduce提交任务
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}
}
