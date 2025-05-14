package collection;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        HashMap<String,String> map= new HashMap<>();
        map.put("A1","B1");
        map.put("A2","B2");
        map.put("A3","B3");
        map.put("A4","B4");

//        Set set1 = map.keySet();
//        Collection col1 = map.values();
//        for(Object obj : set1){
//            System.out.println(obj+"-->"+map.get(obj));
//        }
        for(HashMap.Entry x:map.entrySet()){
            System.out.println(x.getKey() + "-->" + x.getValue());
        }
    }
}
