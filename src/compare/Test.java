package compare;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<Student> ls = new ArrayList<>();
        ls.add(new Student(4,"李四"));
        ls.add(new Student(2,"张三"));
        ls.add(new Student(3,"王五"));
        ls.add(new Student(1,"赵六"));
        ls.add(new Student(5,"张七"));
        //misson1(ls);
        misson2(ls);
    }
    //排序
    public static void misson1(List<Student> ls){
        System.out.println("排序前:");
        print(ls);
        System.out.println("按学号排序后");
        ls.sort(new NumComparator());
        print(ls);

        System.out.println("按姓名排序后");
        ls.sort(new NameComparator());
        print(ls);
    }
    //Stream流
    //选出学生集合中姓张的同学, 同时姓名的长度为 2
    public static void misson2(List<Student> ls){
        System.out.println("选出学生集合中姓张的同学, 同时姓名的长度为 2");
        Stream<Student> s1 = ls.stream();
        s1.filter(s->s.getStuName().startsWith("张")).filter(s->s.getStuName().length()==2);
        s1.forEach(s->System.out.println(s.toString()));
    }

    public static void print(List<Student> ls){
        Iterator itr = ls.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
