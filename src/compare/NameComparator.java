package compare;

import java.util.*;

public class NameComparator implements Comparator{
    public int compare(Object o1,Object o2){
        Student s1 = (Student) o1;
        Student s2 = (Student) o2;
        return s1.getStuName().compareTo(s2.getStuName());
    }

}
