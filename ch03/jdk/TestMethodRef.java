package lambda.java8;

import com.sun.xml.internal.messaging.saaj.soap.SOAPVersionMismatchException;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.*;



public class TestMethodRef {

    //方法引用形式一：  "类名 :: 静态方法名"
    public static void test01(){
        /*
            lambda形式
            BiFunction<Integer,Integer,Integer> bf = (a,b) ->{
                return Math.max(a,b); //仅调用了一个已存在的方法：求a、b的最大值
            };
        */
        //等价的方法引用形式
        BiFunction<Integer, Integer, Integer> bf = Math::max;
        //计算结果并打印
        int result = bf.apply(20, 10);
        System.out.println(result);
    }

    //方法引用形式二： "对象的引用 :: 非静态方法名"
    public static void test02(){
        ArrayList<String> list = new ArrayList<>() ;
        list.add("zs") ;
        /*   lambda形式
             Predicate<String> predicate =  (x) -> list.add(x) ;
        */
        /*
            等价的方法引用形式：
            下条语句中，等号左侧接口中的方法test(): 入参是String，返回值是boolean
            等号右侧add():入参String，返回值是boolean。 二者的入参和返回值一致，因此也可以使用方法引用。
            并且此时，list是一个对象,add是一个非静态方法，因此，本次方法引用的形式是“对象的引用 :: 非静态方法名”
         */
        Predicate<String> predicate =  list::add ;
    }

    //方法引用形式三： "类名 :: 非静态方法"
    public void test03() {
        /*
            lambda形式：
            第一个参数a，正好是equals()方法的调用者；第二个参数b，正好是方法的参数，满足条件。
         */
        BiPredicate<String, String> bp = (a,b) -> a.equals(b) ;
        //等价的方法引用形式：
        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("hello", "hello"));
    }

    //方法引用形式四，构造器引用： "类名 :: new"
    public void test04(){
        //lambda形式1:调用无参构造方法
        Supplier<Person> supplier = () -> {return new Person() ; };//供给型
        //等价的构造器引用形式
        Supplier<Person> s = Person::new ;

        //lambda形式2:调用一个参数的构造方法
        Function<String,Person> function = (name) -> new Person(name) ;
        //等价的构造器引用形式
        Function<String,Person> f = Person::new ;

        //lambda形式3:调用两个参数的构造方法
        BiFunction<String,Integer,Person> f2 = (name,age) -> new Person(name,age) ;
        //等价的构造器引用形式
        BiFunction<String,Integer,Person> f3 = Person::new ;

        //通过构造方法实例化对象举例
        Person per = f3.apply("zs",23) ;
    }


    //方法引用形式五，数组引用： "数组类型[] :: new"
    public void test05(){
        //lambda表达式的形式
        Function<Integer, String[]> fun = num -> new String[num];
        //等价的数组引用的形式
        Function<Integer,String[]> fun1 = String[]::new;

        //使用举例
        String[] strs = fun.apply(10);
        System.out.println(strs.length);
    }

    public void test02_2(){
        //lambda表达式的形式
        PrintStream ps = System.out;
        Consumer<String> con = (str) -> ps.println(str);
        //或者直接写成： Consumer<String> con = (str) -> System.out.println(str);

        //等价的方法引用形式
        Consumer<String> con2 = ps::println;
        //或者直接写成：Consumer<String> con2 = System.out::println; 此种方式更为常见！

        //使用举例
        con2.accept("Hello World");
    }
}
