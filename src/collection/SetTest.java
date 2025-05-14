package collection;

import java.util.Collection;
import java.util.HashSet;

public class SetTest {
    public static void main(String[] args) {
        Collection hashSet = new HashSet();
        hashSet.add("a");
        hashSet.add("a");
        System.out.println("finished");
        for(Object x:hashSet){
            System.out.println(x);
        }
    }
}
