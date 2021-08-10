package mr.mymr;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
//自定义RecordReader
public class MyFileRecorderReader extends RecordReader<NullWritable, Text>  {
	private FileSplit fileSplit  ;
	private Configuration config ;
	private boolean isProcessedFinish = false ;
	private Text value = new Text() ;
	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		//拿到输入数据的文件切片
		fileSplit = (FileSplit)split ;
		config = context.getConfiguration() ;
	}
	//通过重写nextKeyValue()方法，将读取到的文件放入value中
	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if(!isProcessedFinish){
			byte[] buf = new byte[(int)fileSplit.getLength() ] ;
			Path filePath = fileSplit.getPath() ;
			FileSystem fs = filePath.getFileSystem(config) ;
			FSDataInputStream in =null ;
			try{
				in = fs.open(filePath) ;
				IOUtils.readFully(in, buf,0,buf.length);
				
				value.set(buf, 0, buf.length);
			}finally{
				IOUtils.closeQuietly(in);
			}
			isProcessedFinish = true ;
			return true ;
		}
		return false ;
	}
	@Override
	public NullWritable getCurrentKey() throws IOException, InterruptedException {
		return NullWritable.get();
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		return value;
	}
	//getProgress()会返回读取的进度，1.0f代表100%、0.0f代表0%；可据此判断是否已经读取完毕
	@Override
	public float getProgress() throws IOException, InterruptedException {
		return isProcessedFinish? 1.0f : 0.0f ;
	}
	@Override
	public void close() throws IOException {
	}
}
