package mr.common.major.second;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SchoolCommonMajorsReducer extends Reducer<Text, Text, Text, Text> {
	Text v = new Text() ;
	//将两个学校之间的共同专业，以“软件,法学,力学,...”的字符串形式输出
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		StringBuffer  majors= new StringBuffer();
		for(Text value:values){
			majors.append(value.toString()).append(",") ;
		}
		String majorStr = majors.toString().substring(0,majors.length()-1 );
		v.set(majorStr);
		context.write(key, v);
	}
	
}
