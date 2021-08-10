package mr.mymr;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

//自定义InputFormat 
public class MyInputFormat  extends FileInputFormat<NullWritable, Text>{
	//读取到文件后，不用拆分
	 @Override
	 protected boolean isSplitable(JobContext context, Path filename) {
		    return false;
	 }
	 //自定义RecordReader：每次读取一个文件（key:null，value:读取到的文件）
	@Override
	public RecordReader<NullWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		MyFileRecorderReader reader = new MyFileRecorderReader() ;
		reader.initialize(split, context);
		return reader;
	}
}
