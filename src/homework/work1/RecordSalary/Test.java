package homework.work1.RecordSalary;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<String,Double> map = new HashMap<>();
        //工资
        map.put("工资",20000.0);
        //投资
        map.put("投资",10000.0);
        //房租
        map.put("房租",- 2000.0);
        //水电费
        map.put("水电费",- 600.0);
        //饮食购物
        map.put("饮食购物",- 6000.0);
        System.out.println(map.get("水电费"));
        System.out.println(map.get("饮食购物"));
        List<Double> ls = new ArrayList<>(map.values());
        Double Sum = 0.0;
        for(Double x:ls){
            Sum += x;
        }
        System.out.println(Sum);
    }
}
