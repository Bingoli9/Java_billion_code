package tollstation.record;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyTollStation {
	
	static class CarDriveMapper extends Mapper<LongWritable, Text, Text, CarInfoBean>{
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			//将一行内容转成string
			String line = value.toString();
			//得到车辆行驶的各个信息（入站时间、出站时间、行驶公里数...）
			String[] fields = line.split("\t");
			//获取车的类别
			String carType = fields[3];
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
			/*
			 * 输出。 key:车型      value：(行驶距离,行驶时间)
			 * 例如：
			 *      中型       (300,200)
			 *   	小型       (200,105)
			 *   	特大型   (100,65)
			 */
			context.write(new Text(carType), new CarInfoBean(distance,betweenMinutes));
		}
	}
	
	/*
	 * 经过shuffle阶段的排序、分区等流程后，reduce获取到的输入数据类似以下形式：
	 *  key               value
	 *  中型              [  (300,200), (180,106), (500,310),..., (100,56)]
	 *  小型              [  (400,290), (380,196), (200,130),..., (300,210)]
	 * 
	 *  reduce的目标：输出以下形式的数据，
	 *  		车型	   平均行驶公里数    平均行驶时间    平均行驶速度
	 */
	static class CarDriveReduce extends Reducer<Text, CarInfoBean, Text, CarInfoBean>{
		@Override
		protected void reduce(Text key, Iterable<CarInfoBean> values, Context context) throws IOException, InterruptedException {
			//统计车辆个数
			int carCount = 0 ;
			//总行驶公里数
			long sumDistance = 0;
			//总行驶时间
			long sumMinutes = 0 ;
			
			for(CarInfoBean bean: values){
				carCount++ ;
				sumDistance += bean.getDistance() ;
				sumMinutes += bean.getMinutes() ;
			}
			//平均行驶公里数
			long avgDistance = sumDistance/carCount ;//忽略小数
			//平均行驶时间
			long avgMinutes = sumMinutes/carCount ;
			//平均行驶速度
			double avgSpeed = avgDistance/(avgMinutes/60.0) ;
			context.write(key, new CarInfoBean(avgDistance,avgMinutes,avgSpeed));
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MyTollStation.class);
		job.setMapperClass(CarDriveMapper.class);
		job.setReducerClass(CarDriveReduce.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CarInfoBean.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(CarInfoBean.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
	}

}
