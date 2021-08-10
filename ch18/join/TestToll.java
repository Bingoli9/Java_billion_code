package tollstation.record.join;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestToll {
	public static void productCarInfo() throws ParseException{
		for (int j = 0; j < 500; j++) {

			// 时间
			String todayStr = "2018-09-15.00:00:00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
			Date today = sdf.parse(todayStr);

			Date start = new Date(today.getTime() + (long) (Math.random() * 18 * 60 * 60 * 1000));
			// Date -> String
			String startStr = sdf.format(start);
			System.out.print(startStr + "\t");
			
			long endLong = start.getTime() + (long)(Math.random()*6*3600*1000) ;
			Date endDate = new Date(endLong);
			
			String endStr = sdf.format(endDate);
			System.out.print(endStr + "\t");
			
//			s = vt
			long s = (long)((endLong - start.getTime() *1.0) /1000/3600 *  ( 70 + (long)(Math.random()*50) ));
			System.out.print(s + "\t");
			// System.out.println( new Date(0)); //new
			// Date(millionSeconds)：从1970.1.1 0:0:0.000
			// 开始经过millionSeconds毫秒之后的时间
			

			String[] types = { "小型", "中型","大型","特大型" };
			System.out.print(types[(int) (Math.random() * 4)] + "\t");
			

			String[] provinces = { "京", "冀", "蒙", "吉", "沪", "浙", "闽", "鲁", "鄂", "粤", "琼", "川", "云", "陕", "青", "新" };
			String firstStr = provinces[(int) (Math.random() * provinces.length)];

			String[] chStrs = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K" };
			String secondStr = chStrs[(int) (Math.random() * chStrs.length)];

			int charactorCount = 0;// 车牌
			// 2
			// 川A xxxxx
			String[] rearStrs = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S",
					"T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
			String rear = "";

			for (int i = 3; i <= 7; i++) {
				if (charactorCount == 2) {
					rear += (int) (Math.random() * 10);
					continue;
				}

				int ran = (int) (Math.random() * rearStrs.length);
				rear += rearStrs[ran];
				if (ran < 26)
					charactorCount++;

			}
			String carPlate = firstStr + secondStr + rear;
			System.out.println(carPlate );
		

//			String[] driverDirecs = { "驶入", "驶出" };
//			System.out.print(driverDirecs[(int) (Math.random() * 2)] + "\t" );
			
//			System.out.println((int)(Math.random() * 300) +5) ;

		}
	}
	
	//id	车型		pl	
	public static void productCarType(){
		
		System.out.println(1+"\t小型\t"+1.5);
		System.out.println(2+"\t中型\t"+2.5);
		System.out.println(3+"\t大型\t"+3.5);
		System.out.println(4+"\t特大型\t"+4.5);
	}
	
	public static void main(String[] args) throws ParseException {
//		productCarInfo();
		productCarType();
	}
}
