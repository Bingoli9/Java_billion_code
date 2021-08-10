package mr.common.major.first;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SchoolCommonMajorsMapper extends Mapper<LongWritable, Text, Text, Text> {
	Text k = new Text();
	Text v = new Text();
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString().trim() ;
		String[] infos  = line.split(":") ;
		
		String school =  infos[0] ;
		String[] majors = infos[1].split("\t") ; 
		for(String major :majors){
			k.set(major);
			v.set(school);
			context.write( k, v);
		}
	}
}
