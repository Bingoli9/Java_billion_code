package tollstation.record.join;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyTollStation {
		static class TollStationMapper extends Mapper<LongWritable, Text, Text, CarInfoBean> {
			CarInfoBean bean = new CarInfoBean();
			Text k = new Text();
			@Override
			protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
				//获取输入的文件名，区分tollstation.txt和cartype.txt
				FileSplit inputSplit = (FileSplit) context.getInputSplit();
				String name = inputSplit.getPath().getName();
				
				String line = value.toString();
				String carTypeId = "";
				
				String[] fields = line.split("\t");
				//将tollstation.txt中的数据，放入JavaBean
				if (name.startsWith("tollstation")) {
					//2个文件都包含的“车型id”，作为map的KEYOUT
					carTypeId = fields[3];
					String startTime = fields[0] ;
					String endTime = fields[1] ;
					long distance = Integer.parseInt(fields[2]) ;
					String carPlate = fields[4] ;
					int flag = 0 ;
					//行驶信息的字段，放入JavaBean (cartype.txt字段的值用""、0.0f等初始值占位) ，作为map的VALUEOUT
					bean.setBean(startTime,endTime,distance,carPlate,"",0.0f,flag);
				} else {
					//将车型信息中的数据，放入JavaBean
					//2个文件都包含的“车型id”，作为map的KEYOUT
					carTypeId = fields[0] ;
					String carTypeName = fields[1] ;
					float pl = Float.parseFloat(fields[2])  ;
					int flag = 1 ;
					//车型信息的字段，放入JavaBean (行驶信息字段的值用""、0等初始值占位) ，作为map的VALUEOUT
					bean.setBean("","",0,"",carTypeName,pl,flag);
				}
				k.set(carTypeId);
				context.write(k, bean);
			}
		}

		static class TollStationReducer extends Reducer<Text, CarInfoBean, CarInfoBean, NullWritable> {
			@Override
			protected void reduce(Text carTypeId, Iterable<CarInfoBean> beans, Context context) throws IOException, InterruptedException {
				//每次reduce获取的数据中，只有一条车型信息
				CarInfoBean carTypeBean = new CarInfoBean();
				//每次reduce获取的数据中，有多条行驶信息
				ArrayList<CarInfoBean> tollStationBeans = new ArrayList<CarInfoBean>();

				for (CarInfoBean bean : beans) {
					//车型信息
					if (bean.getFlag() == 1) {
						try {
							//将车型信息组装到carTypeBean中
							carTypeBean.setCarTypeBean(bean.getTypeName(), bean.getPl());
					} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						//行驶信息
						CarInfoBean car = new CarInfoBean();
						try {
							//将行驶信息组装到carTypeBean中
							car.setTollStationBean(bean.getStartTime(), bean.getEndTime(), bean.getDistance(), bean.getCarPlate() );
							tollStationBeans.add(car);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				// 组装两种JavaBean形成最终结果(将CarTypeBean的内容，组装到TollStationBean中)
				for (CarInfoBean bean : tollStationBeans) {
					bean.setTypeName(carTypeBean.getTypeName());
					bean.setPl(carTypeBean.getPl());
					context.write(bean, NullWritable.get());
				}
			}
		}
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MyTollStation.class);
		job.setMapperClass(TollStationMapper.class);
		job.setReducerClass(TollStationReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CarInfoBean.class);
		
		job.setOutputKeyClass(CarInfoBean.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
	}
}
