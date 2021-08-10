package tollstation.record.group;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyTollStation {
	static class CarDriveMapper extends Mapper<LongWritable, Text, CarInfoBean, NullWritable>{
		CarInfoBean car = new CarInfoBean();
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			//将一行内容转成string
			String line = value.toString();
			//得到车辆行驶的各个信息（入站时间、出站时间、行驶公里数...）
			String[] fields = line.split("\t");
			//获取车牌
			String carPlate = fields[fields.length-1];
			//获取行驶公里数
			long distance = Long.parseLong(fields[2]);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss") ;
			//进站时间
			String startDateStr = fields[0] ;
			//出站时间
			String endDateStr = fields[1] ;
			Date startDate = null ; 
			Date endDate = null ; 
			try {
				 startDate = sdf.parse(startDateStr ) ;
				 endDate = sdf.parse(endDateStr ) ;
			} catch (ParseException e) {
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//计算行驶时间（单位：分钟）
			long betweenMinutes = (int)((endDate.getTime() - startDate.getTime())/1000/60);
			//将车辆信息封装到JavaBean中
			car.setCarPlate(carPlate);
			car.setMinutes(betweenMinutes);
			car.setDistance(distance);
			car.setSpeed(distance*1.0/betweenMinutes*60 );
			context.write(car, NullWritable.get());
		}
	}
	static class CarDriveReduce extends Reducer<CarInfoBean, NullWritable, CarInfoBean, NullWritable>{
		@Override
		protected void reduce(CarInfoBean key, Iterable<NullWritable> values,Context context)
				throws IOException, InterruptedException {
			
			context.write(key,NullWritable.get() );
		}
	}
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MyTollStation.class);
		job.setMapperClass(CarDriveMapper.class);
		job.setReducerClass(CarDriveReduce.class);
		//修改输出类型
		job.setMapOutputKeyClass(CarInfoBean.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(CarInfoBean.class);
		job.setGroupingComparatorClass(PrvoinceGroup.class);
		
		job.setInputFormatClass(CombineTextInputFormat.class);
		/*
		 * 设置切片的大小介于 100M-128M之间。注意，这里是指：当多个小文件合并后，如果正好介于 100M-128M，
		 * 就将这些小文件合并。但是，如果全部的小文件合并后仍然<100MB，也是会将全部小文件合并成一个切片。
		 */
		CombineTextInputFormat.setMinInputSplitSize(job,100*1024*1024 );
		CombineTextInputFormat.setMaxInputSplitSize(job,128*1024*1024 );
		
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
	}
}