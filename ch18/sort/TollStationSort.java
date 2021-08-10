package tollstation.record.sort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

//根据行驶时间，对第1次的结果进行降序
public class TollStationSort {
	static class TollStationSortMapper extends Mapper<LongWritable, Text, CarInfoBean, Text> {

		CarInfoBean carBean = new CarInfoBean();
		Text v = new Text();
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			//map的输入数据格式（第1问的输出结果）：中型	288	185	93.4054054054054
			String line = value.toString();
			String[] fields = line.split("\t");
			//车型
			String carType = fields[0];
			//平均行驶公里数
			long distance =Long.parseLong(fields[1]);
			//平均行驶时间
			long minute =Long.parseLong(fields[2]);
			//平均行驶速度
			double speed = Double.parseDouble(fields[3]);
			//将平均行驶公里数、平均行驶时间和平均行驶速度，封装到一个bean中
			carBean.setBean(distance,minute,speed);
			v.set(carType);
			/*
			 * 因为shuffle阶段会根据“key”排序，因此必须将需要排序的字段放到key中；
			 * 并且会根据carBean中的compareTo()方法排序
			 */
			context.write(carBean,v);
		}
	}

	/*
	 * 经过shuffle阶段的排序、分区等流程后，reduce获取到的输入数据类似以下形式：
	 *            key               value
	 *  267	171	93.68421052631578	[小型]	
	 *  270	174	93.10344827586208	[特大型]	
	 *	281	181	93.14917127071823	[大型]	
	 *	288	185	93.4054054054054	[中型]	
	 *	
	 *	因为，map输出的key没有重复值、无需合并key，因此reduce得到的value中也就只有一个元素。
	 * 
	 * reduce的目标：输出以下形式的数据(根据行驶时间升序)，
	 *  		车型	   平均行驶公里数    平均行驶时间    		平均行驶速度
	 *  例如：     267		171			93.68421052631578	  [小型]	
	 *  
	 *  因此，reduce的处理逻辑就是：将拿到的每条数据的key和value交换位置即可
	 */
	static class TollStationSortReducer extends Reducer<CarInfoBean, Text, Text, CarInfoBean> {

		@Override
		protected void reduce(CarInfoBean bean, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			//将key和value交换位置
			context.write(values.iterator().next(), bean);
		}
	}
	
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		//指定本程序的jar包所在的本地路径
		job.setJarByClass(TollStationSort.class);
		
		//指定本业务job要使用的mapper/Reducer业务类
		job.setMapperClass(TollStationSortMapper.class);
		job.setReducerClass(TollStationSortReducer.class);
		
		//指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(CarInfoBean.class);
		job.setMapOutputValueClass(Text.class);
		
		//指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(CarInfoBean.class);
		
		//指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//指定job的输出结果所在目录
		
		Path outPath = new Path(args[1]);
		/*FileSystem fs = FileSystem.get(conf);
		if(fs.exists(outPath)){
			fs.delete(outPath, true);
		}*/
		FileOutputFormat.setOutputPath(job, outPath);
		
		//将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
		/*job.submit();*/
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
	}
}
