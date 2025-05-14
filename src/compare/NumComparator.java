package compare;

import java.util.Comparator;

//新建比较器
public class NumComparator implements Comparator {
    public int compare(Object o1,Object o2){
        return ((Student)o1).getStuNum() - ((Student)o2).getStuNum();
    }
}

