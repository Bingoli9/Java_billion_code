package lambda.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class TestStreamAPI {

    //创建Stream
    public void test01() {
        //通过Collection提供的stream()和parallelStream()方法创建流
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        //将list中的元素以"流元素"的形式，存放于Stream中
        Stream<String> strStream = list.stream();
        Stream<String> strParallelStream = list.parallelStream();

        String[] strs = new String[]{"hello", "world"};
        //通过Arrays提供的stream()方法创建流
        Stream<String> strStreamWithOfArray = Arrays.stream(strs);
        //通过Stream类提供的of()、iterate()和generate()方法创建流
        Stream<String> strStreamWithOf = Stream.of(strs);
        //创建无限流0、100、200、300、……，但仅仅获取前5个元素
        Stream<Integer> strStreamWithIterate = Stream.iterate(0, (x) -> ++x).limit(5);
        //创建无限流（包含无限个UUID），但仅仅获取前3个
        Stream<UUID> uUidStreamWithGenerate = Stream.generate(UUID::randomUUID).limit(3);
        //创建IntStream流
        IntStream intStream = IntStream.of(new int[]{1, 2, 3});
    }

    //转换Stream操作
    public void test02() {
        //----------------limit()操作-------------------------
        //产生以0开始偶数组成的流，并且只使用前5个数字
        Stream<Integer> stream = Stream.iterate(0, x -> x + 2).limit(5);
        System.out.println("limt(5),截取无限流中的前5个元素：");
        stream.forEach(x -> System.out.print(x + "\t"));

        /*
            注意：一个Stream对象只能被终端操作使用一次，而上述的forEach()就是一个终端操作（用于打印流中的各个元素）。
            因此，后续如果还要使用Stream对象，就必须重新生成。

         */
        //----------------filter()操作-------------------------
        /*
            对产生的流进行过滤filter操作：筛选出比5大的数字;
            以下语句等价于stream.filter( (x)->x>5  ).forEach(   (x) -> System.out.println(x)  );
         */
        Stream<Integer> stream1 = Stream.iterate(0, x -> x + 2).limit(5);
        System.out.println("\nfilter((x) -> x > 3),筛选无限流中，大于3的元素：");
        stream1.filter((x) -> x > 3).forEach(x -> System.out.print(x + "\t"));
        //----------------limit()操作-------------------------
        //一个 Stream 只可以使用一次
        Stream<Integer> stream2 = Stream.iterate(0, x -> x + 2).limit(5);
        System.out.println("\nskip(2),跳过无限流中的前2个元素：");
        stream2.skip(2).forEach(x -> System.out.print(x + "\t"));

        //----------------distinct()操作-------------------------
        Stream<Integer> stream3 = Stream.of(1, 1, 2, 2, 3, 4);
        //删除流中重复的元素：如果流中元素为简单类型（8个基本类型+String），则distinct()会自动去重
        System.out.println("\ndistinct(),删除无限流中的重复元素（简单类型）：");
        stream3.distinct().forEach(x -> System.out.print(x + "\t"));

        //删除流中重复的元素：如果流中元素为对象类型，需要通过“重写hashCode()和equals()”来告诉来告诉程序什么样的对象可以作为同一个元素（例如，可以认为:当name和age相同时，就作为同一个对象）
        Person[] pers = new Person[]{new Person("zs", 23), new Person("zs", 23), new Person("ls", 24)};
        System.out.println("\ndistinct(),删除无限流中的重复元素（对象类型）：");
        Stream.of(pers).distinct().forEach(x -> System.out.print(x + "\t"));

        //----------------map()操作：将每个元素进行了一次映射操作（转换操作），因为是每个元素都有映射后的产物，所以是map()是一对一的。-------------------------
        //将Stream中的每个单词，转为与之对应的大写单词
        Stream<String> stream4 = Stream.of("hello", "world", "hello", "stream");
        System.out.println("\nmap(),将Stream中的每个单词转为大写：");
        stream4.map(str -> str.toUpperCase()).forEach(x -> System.out.print(x + "\t"));

        //map常用于部分内容的提取：例如,从Stream的各个Person对象中提取出name属性
        Person[] pers5 = new Person[]{new Person("ww", 25), new Person("zl", 26), new Person("zs", 23), new Person("ls", 24)};
        Stream<Person> stream5 = Stream.of(pers5);
        System.out.println("\nmap(),提取各个Person中的name属性：");
        stream5.map(per -> per.getName()).forEach(x -> System.out.print(x + "\t"));

        //----------------flatMap()操作-------------------------
        //将Stream中所有单词转为字母形式，并删除重复的字母。例如，将"ab","bc"转为"a","b","b","c"，再删除一个重复的"b"，最终显示"a","b","c"
        Stream<String> stream6 = Stream.of("hello", "world", "hello", "stream");
        System.out.println("\nflatMap(),打印所有字符串中出现的字母，要求不重复：");
        //将流中的每个字符串，都映射成为Stream<String[]>，即{"h","e","l","l","o"},{"w","o","r","l","d"},...，即每个流元素都是一个String数组
        stream6.map(str -> str.split(""))
                //将上一步map()后的产物，全部纳入到一个“容器”中，即{"h","e","l","l","o","w","o","r","l","d",..,"e","a","m"}，注意此时只有一个数组。可以发现,flatMap()是将多个String数组，转为了一个String数组，因此是多对一的。
                .flatMap(str -> Arrays.stream(str))
                //对上一步flatMap()的产物去重
                .distinct().forEach(x -> System.out.print(x + "\t"));

        /*
            排序操作：
                    在Stream中，可以使用sorted()或sorted(Comparator<T>)进行排序
		            1.如果调用sorted()，则使用的是Comparable排序；
		             sorted()方法会调用排序元素所重写的comparator()方法，根据comparator()中的规则进行排序。
		             例如，如果排序的是Person类型的元素，就需要先让Person实现则使用的是Comparable排序接口，并重写comparator()方法，如下
        */
        //----------------内部排序sorted()操作-------------------------
        Person[] pers7 = new Person[]{new Person("ww", 25), new Person("zs", 23), new Person("ww", 22), new Person("ls", 24)};
        System.out.println("\nsorted(),使用Comparable中的comparator()方法，对流中的Person对象排序（按age从小到大）：");
        Stream.of(pers7).sorted().forEach(System.out::println);
        //JDK提供的很多类（如String）自身已经实现了Comparable接口，因此可以直接对其进行sort()排序，如下：

        String[] strs = new String[]{"bb", "cc", "aa", "dd"};
        System.out.println("\nsorted(),对流中的字符串进行排序（String自身实现了Comparable接口，可以按字典顺序排序）：");
        Stream.of(strs).sorted().forEach(str -> System.out.print(str + "\t"));

        /*
                     2.如果调用sorted(Comparator<T>)，则使用的是Comparator排序;
                     sorted(Comparator<T>)方法会使用参数中Comparator接口的compare()方法进行排序。
         */
        System.out.println("\nsorted(Comparator<T>),使用Comparator中的compare()方法，对流中的Person对象排序（按age从小到大）：");
        Stream.of(pers7).sorted((a, b) -> Integer.compare(a.getAge(), b.getAge())).forEach(System.out::println);

        //还可以使用sorted()或sorted(Comparator<T>)进行多次排序，例如：对流中的Person对象，先按name排序，如果name相同再按age排序，如下
        System.out.println("\nsorted(Comparator<T>),二次排序（先name，再age）：");
        Stream.of(pers7).sorted((a, b) ->
        {
            if (!a.getName().equals(b.getName())) {//排序规则：先name，再age
                //调用String内部提供的比较方法
                return String.CASE_INSENSITIVE_ORDER.compare(a.getName(), b.getName());
            } else {
                //调用Integer提供的比较方法
                return Integer.compare(a.getAge(), b.getAge());
            }
        })
                .forEach(System.out::println);
    }

    static Person[] pers = null;

    static {
        pers = new Person[]{new Person("ww", 25), new Person("zl", 26), new Person("zl", 22), new Person("zs", 23), new Person("ls", 24)};
    }

    //终端操作
    public void test03() {
        //判断是否全部人的age都大于24
        boolean result = Stream.of(pers).map(per -> per.getAge()).allMatch(age -> age > 24);
        System.out.println("allMatch()：" + result);

        //判断是否存在大于24的age (即只要有比24大的age即可)
        boolean result1 = Stream.of(pers).map(per -> per.getAge()).anyMatch(age -> age > 24);
        System.out.println("anyMatch()：" + result1);

        //判断是否没有age比24大（即不存在大于24的age）
        boolean result2 = Stream.of(pers).map(per -> per.getAge()).noneMatch(age -> age > 24);
        System.out.println("noneMatch()：" + result2);

        //获取流中第一个元素
        Optional<Integer> firstAge = Stream.of(pers).map(per -> per.getAge()).findFirst();
        System.out.println("firstAge()：" + firstAge.get());

        //获取流中任一个元素（不是随机一个元素，而是根据某个算法找到的一个元素；即：如果对同一个流多次进行findAny()操作，那么得到的将是同一个元素）
        Optional<Integer> certainAge = Stream.of(pers).map(per -> per.getAge()).findAny();
        System.out.println("findAny()：" + certainAge.get());

        //获取“最大”的元素。“最大”是由max(Comparator<T>)方法的参数约定
        Optional<Person> maxPerson = Stream.of(pers).max((a, b) -> a.getAge() > b.getAge() ? 1 : (a.getAge() < b.getAge() ? -1 : 0));
        System.out.println("max()：" + maxPerson.get().getAge());

        //reduce,聚合流中的所有元素：本次是将流中的全部age值，聚合为一个值（所有age之和）
        Integer reduceAge = Stream.of(pers).map(per -> per.getAge()).reduce(0, (a, b) -> a + b);
        System.out.println("reduce()：" + reduceAge);
		/*
		本程序中的reduce( 0,(a,b)->a+b )表示求和，类似于以下for()求和方式。其中，a相当于sum,b相当于i。
		int sum = 0;
		for(int i=0;i<100;i++){
			sum += i ;
		}
		*/

        //求名字中s出现的次数
        Integer sCount = Stream.of(pers).map(per -> per.getName()).map(str -> str.split(""))
                .flatMap(str -> Arrays.stream(str))
                //先将每个字母映射数字0或1（如果是s，则映射为1；否则为0），再累加全部的数字（只有s才是有效的累加；因为其他字母是0，对0进行累加是没有效果的）
                .map(a -> a.equals("s") ? 1 : 0).reduce(0, (x, y) -> x + y);
        System.out.println("s出现的次数是：" + sCount);

        /*----collect()聚合操作：将流的元素聚合成一个集合(或一个值)-----*/
        //聚合成一个List集合
        List<String> nameList = Arrays.stream(pers).map(per -> per.getName()).collect(Collectors.toList());
        System.out.println("collect(Collectors.toList()：" + nameList);

        //聚合成一个Map集合：实现按key分组(根据年龄分组)
        Map<Integer, List<Person>> ageMap = Arrays.stream(pers).collect(Collectors.groupingBy(Person::getAge));
        System.out.println("Collectors.groupingBy(Person::getAge)：\n" + ageMap);

        //聚合成一个Map集合：实现按key分区(根据age是否大于24)
        Map<Boolean, List<Integer>> agePartition = Arrays.stream(pers).map(Person::getAge).collect(Collectors.partitioningBy(age -> age > 24));
        System.out.println("Collectors.partitioningBy( age -> age>24 )：" + agePartition);

        //聚合成一个值：求最小值(也可以是最大值、个数、求和等聚合计算)
        Optional<Integer> minAge = Arrays.stream(pers).map(per -> per.getAge()).collect(Collectors.minBy(Integer::min));
        System.out.println("Collectors.minBy(Integer::min)：" + minAge);

        //聚合成一个值：连接操作，将流中的多个值连接成一个字符串，并加上前缀和后缀。
        String allNameInOneStr = Arrays.stream(pers).map(Person::getName).collect(Collectors.joining(",", "**prefix**", "**suffix**"));
        System.out.println("Collectors.joining(...)" + allNameInOneStr);
    }


    public static void main(String[] args) {
//		new TestStreamAPI().test01() ;
//		new TestStreamAPI().test02() ;
        new TestStreamAPI().test03();
    }
}
