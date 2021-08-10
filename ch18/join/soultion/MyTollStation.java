package tollstation.tilt.soultion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

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
	static class MyTollStationMapper extends Mapper<LongWritable, Text, CarInfoBean, NullWritable> {
		// 将车型信息放入到hashMap中
		HashMap<String, CarInfoBean> hashMap = new HashMap<>();
		CarInfoBean typeBean;
		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			// 在MR执行前，已经通过main()中的job对象将cartype.txt文件放在了MR中；因此，可以在map()初始化阶段，直接取出该文件
			BufferedReader reader = new BufferedReader(new FileReader("cartype.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("\t");
				String carTypeId = fields[0];
				typeBean = new CarInfoBean();
				typeBean.setCarTypeBean(fields[1], Float.parseFloat(fields[2]));
				// 将carTypeId作为key，carTypeName和pl以bean的形式作为value
				hashMap.put(carTypeId, typeBean);
			}
			reader.close();
		}

		CarInfoBean carBean ;
		// 在map阶段，只接收tollstation.txt文件
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			// 1.获取行驶信息
			String fields[] = value.toString().split("\t");
			if (fields.length == 5) {
				String startTime = fields[0];
				String endTime = fields[1];
				long distance = Long.parseLong(fields[2]);
				String carTypeId = fields[3];
				String carPlate = fields[4];

				// 2.从hashMap中,获取对应的车型信息(carTypeName和pl)
				 carBean = hashMap.get(carTypeId);
				// 3将行驶信息和车型信息进行合并
				if (carBean != null) {
					carBean.setTollStationBean(startTime, endTime, distance, carPlate);
				}
				
			}
			context.write(carBean, NullWritable.get());
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MyTollStation.class);
		job.setMapperClass(MyTollStationMapper.class);
		// 本程序没有使用reduce
		job.setNumReduceTasks(0);
		// job.setReducerClass(MyTollStationMapper.class);

		// 预处理：在MR执行前，预先将cartype.txt放置到hdfs中
		job.addCacheFile(new URI("hdfs://bigdata01:9000/inputfiles/cartype.txt"));
		
		job.setMapOutputKeyClass(CarInfoBean.class);
		job.setMapOutputValueClass(NullWritable.class);
		// 指定最终输出的数据的kv类型
		job.setOutputKeyClass(CarInfoBean.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean res = job.waitForCompletion(true);
		System.exit(res ? 0 : 1);
	}
}
