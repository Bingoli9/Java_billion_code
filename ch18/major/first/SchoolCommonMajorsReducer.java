package mr.common.major.first;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SchoolCommonMajorsReducer extends Reducer<Text, Text, Text, Text> {
	Text k = new Text();
	Text v = new Text();

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		List<String> list = new ArrayList<>();
		for(Text major : values){
			list.add(major.toString()) ;
		}
		//排序
		Collections.sort(list);
		//把values两两组合，并以的“A-B”形式显示
		   for(int i=0;i< list.size()-1;i++)
		     {
		          for(int j=i+1;j< list.size();j++)
		          { 
		        	  k.set(list.get(i)+"-"+list.get(j) );
		        	  v.set(key);
		        	  context.write( k,v);   
		          }
		     }
	}
	
}
