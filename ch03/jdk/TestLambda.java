package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface MyMath {
    int add(int num1, int num2);
}

interface MyMath2 {
    int addSelf(int num);
}

public class TestLambda {
    public static void main(String[] args) {
        //示例1
        //传统写法
        MyMath math = new MyMath() {
            public int add(int num1, int num2) {
                return num1 + num2;
            }
        };
        //lambda：将能省略的代码都进行了省略；以上代码的等价写法
        MyMath m = (int num1, int num2) -> {
            return num1 + num2;
        };
        /*
           lambda可以自定识别数据类型，因此int也可以省略，
           即可以改写为： MyMath math1 = ( num1, num2) ->{ return num1+num2 ;} ;
         */
        System.out.println("示例1结果：" +math.add(3, 5));

        /*--------------------------------------------*/
        //示例2
        //传统写法
        MyMath2 math2 = new MyMath2() {
            @Override
            public int addSelf(int num) {
                return num + 1;
            }
        } ;
        //改写为lambda表达式形式
        MyMath2 m2 = (num) -> {
            return num + 1;
        };
        /*
           如果方法只有1个参数，则可以将()和return也省略，
           即以上等价于MyMath2 没 = x -> x+1;
         */
        System.out.println("示例2结果：" + m2.addSelf(10));

        /*--------------------------------------------*/
        //示例3:使用JDK提供的消费型接口
        //传统写法
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("示例3结果：吃"+ s) ;
            }
        } ;
        //改写为lambda表达式形式
        Consumer<String> c = x-> System.out.println("示例3结果：吃"+ x) ;
        //执行示例3
        c.accept("apple");

        /*--------------------------------------------*/
        //示例4:使用JDK提供的供给型接口
        //传统写法
        Supplier<Integer> supplier =  new Supplier<Integer>() {
            @Override
            public Integer get() {
                return  (int)(Math.random()*9000+1000);//返回一个四位随机数
            }
        } ;
        //改写为lambda表达式形式
        Supplier<Integer> sup =  () ->{return  (int)(Math.random()*9000+1000);} ;
        //或 Supplier<Integer> sup =  () ->{(int)(Math.random()*9000+1000)} ;
        System.out.println("示例4结果：" +sup.get());

        /*--------------------------------------------*/
        //示例5:使用JDK提供的函数型接口
        //传统写法
        Function function =   new Function<String,String>(){
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
        //改写为lambda表达式形式
        Function<String,String> f = (s) ->{
            return s.toUpperCase();
        };
        System.out.println( "示例5结果：" + f.apply("Hello World") );

        /*--------------------------------------------*/
        //示例6:使用JDK提供的断言型接口
        //传统写法
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return  num<10; //如果传入的num<10，则断言为true；否则断言为false
            }
        } ;
        //改写为lambda表达式形式
        Predicate<Integer> p =  num -> num<10  ;
        System.out.println( "示例6结果：" + p.test(7) );


        /*--------------------------------------------*/
        /*
             示例5的等价写法
         */
        /*
            将lambda表达式作为一个方法的入参；
            Function接口中抽象方法apply()的方法体，就是由lambda表达式进行的重写
        */
        String upperStr = upper((str) -> str.toUpperCase(),"hello world");
        /*--------------------------------------------*/
        /*
             示例6的等价写法
         */
        myPredicte(num -> num<10,7);
    }
    public static String upper(Function<String, String> fun,String str){
        return fun.apply(str);
    }
    public static void myPredicte(Predicate<Integer> pre,Integer num){
        System.out.println(  pre.test(num));
    }
	public void testLambda {
		IntConsumer ic = (int x) -> System.out.println(x) ;
		ic.accept(10);

		Consumer<String> c = (String x) -> System.out.println(x) ;
		c.accept("hello");
		//以上两个lambda表达式，右侧的lambda可以根据指定的输入参数类型int或String，判断x的具体类型。

	   IntConsumer ic2 = (x) -> System.out.println(x) ;
		ic2.accept(10);

		Consumer<String> c2 = (x) -> System.out.println(x) ;
		c2.accept("hello");
	   //以上两个lambda表达式完全相同，因此只通过lambda的“类型推断”机制，根据上下文自动判断类型是String或int
	}
}
