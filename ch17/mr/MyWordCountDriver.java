package mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import mr.MyWordCountMapper.MyCount;

//设置MapReduce程序的各种参数，最后以job的形式提交到MapReduce中执行
public class MyWordCountDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		//开启map阶段的压缩功能
		conf.setBoolean("mapreduce.map.output.compress", true);
		//设置压缩方式
		conf.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);
		
		
		Job job = Job.getInstance(conf);
		
		//MapReduce会根据传入的class参数找到job依赖的jar包
		job.setJarByClass(MyWordCountDriver.class);
		
		job.setCombinerClass(MyWordCountReducer.class  );//本机 局部求和
		
		//指定本程序的Map、Reduce类
		job.setMapperClass(MyWordCountMapper.class);
		job.setReducerClass(MyWordCountReducer.class);//全局求和
	
		//指定map输出数据的key/value类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//指定reduce输出数据的key/value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//指定需要处理文件的hdfs路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));//a.txt
		//在MapReduce计算完毕后，将结果输出到hdfs的文件路径
		FileOutputFormat.setOutputPath(job, new Path(args[1]));//b.txt
		
		//开启reduce阶段的压缩功能
		FileOutputFormat.setCompressOutput(job, true);
		//设置压缩方式
		FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
		
		//向MapReduce提交任务
		boolean result = job.waitForCompletion(true);
		
		//必须在Job执行完毕后使用
		System.out.println( job.getCounters().findCounter(MyCount.ERROR).getValue() );;
		System.out.println(job.getCounters().findCounter(MyCount.NORMAL).getValue()); ;
		
	
		System.exit(result?0:1);
	}
}
