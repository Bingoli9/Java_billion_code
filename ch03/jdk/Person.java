public class Person implements Comparable<Person> {//如果使用sorted()排序，使用该行
    //public class Person {//如果使用sorted(Comparator<T>)排序,使用该行
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //省略setter、getter...
   //以下是IDEA自动重写的equals()和hashCode()方法，就是根据当前类的属性name、age是否
相同来判断是否是同一个对象
    @Override
    public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Person person = (Person) o;
         return age == person.age && Objects.equals(name, person.name);
    }
    @Override
    public int hashCode() {
         return Objects.hash(name, age);
    }

    @Override
    public String toString() {
         return "Person{"+
                  "name='"+ name + '\'' +
                  ", age="+ age +
                  '}';
    }

    @Override
    public int compareTo(Person p) {
         if(this.getAge() > p.getAge())return 1 ;
         else if(this.getAge() < p.getAge())return -1 ;
         else return 0;
    }
}