package homework.work1.StudentClass;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        HashSet<Student> hs = new HashSet<>();
        hs.add(new Student("Mike",20));
        hs.add(new Student("john",12));
        hs.add(new Student("Mike",20));
        hs.add(new Student("smith",30));
        hs.add(new Student("Mike",18));
        //输出hs
        for(Student s:hs){
            System.out.println(s.getName()+" "+s.getAge());
        }
    }
}
