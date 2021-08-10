public class TestBiFunction
{
	
	public static void test01(){
       BiFunction<Integer,Integer,Integer> bf = (a,b) ->{
          return Math.max(a, b); //仅引用了一个已存在的方法，用于求a、b的最大值
      };
	}

}