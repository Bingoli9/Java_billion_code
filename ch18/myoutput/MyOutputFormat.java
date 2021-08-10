package mr.myoutput;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyOutputFormat extends FileOutputFormat<Text, NullWritable> {

	@Override
	public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext context)
			throws InterruptedException, IOException {
		FileSystem fs = FileSystem.get(context.getConfiguration());
		// 将数据，根据车型分别数据到以下文件中
		Path smallCarPath = new Path("hdfs://bigdata01:9000/tolllstation/small.txt");
		Path normalCarPath = new Path("hdfs://bigdata01:9000/tolllstation/nomal.txt");
		Path bigCarPath = new Path("hdfs://bigdata01:9000/tolllstation/big.txt");
		Path superCarPath = new Path("hdfs://bigdata01:9000/tolllstation/super.txt");

		FSDataOutputStream smallCarOut = fs.create(smallCarPath);
		FSDataOutputStream normalCarOut = fs.create(normalCarPath);
		FSDataOutputStream bigCarOut = fs.create(bigCarPath);
		FSDataOutputStream superCarOut = fs.create(superCarPath);

		return new MyRecordWriter(smallCarOut, normalCarOut, bigCarOut, superCarOut);
	}

	static class MyRecordWriter extends RecordWriter<Text, NullWritable> {

		FSDataOutputStream smallCarOut = null;
		FSDataOutputStream normalCarOut = null;
		FSDataOutputStream bigCarOut = null;
		FSDataOutputStream superCarOut = null;

		public MyRecordWriter(FSDataOutputStream smallCarOut, FSDataOutputStream normalCarOut,
				FSDataOutputStream bigCarOut, FSDataOutputStream superCarOut) {
			this.smallCarOut = smallCarOut;
			this.normalCarOut = normalCarOut;
			this.bigCarOut = bigCarOut;
			this.superCarOut = superCarOut;
		}

		// MR最终是通过write()输出文件，且write()的参数值 就是文件中的每行内容
		@Override
		public void write(Text key, NullWritable value) throws InterruptedException, IOException {
			String line = key.toString().trim();
			String[] carInfo = line.split("\t");

			String carType = carInfo[3];
			switch (carType) {
			case "小型":
				smallCarOut.write((line + "\r\n").getBytes());
				break;
			case "中型":
				normalCarOut.write((line + "\r\n").getBytes());
				break;
			case "大型":
				bigCarOut.write((line + "\r\n").getBytes());
				break;
			case "特大型":
				superCarOut.write((line + "\r\n").getBytes());
				break;
			default:
				System.out.println("其他...");
				break;
			}
		}

		@Override
		public void close(TaskAttemptContext context) throws InterruptedException, IOException {
			if (smallCarOut != null) {
				smallCarOut.close();
			}
			if (normalCarOut != null) {
				normalCarOut.close();
			}
			if (bigCarOut != null) {
				bigCarOut.close();
			}
			if (superCarOut != null) {
				superCarOut.close();
			}

		}
	}
}