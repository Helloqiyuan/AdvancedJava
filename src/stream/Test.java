package stream;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] a = new int[1000];
        Random random = new Random();
        for(int i = 0;i < a.length;i++){
            a[i] = random.nextInt();
        }
        long startTime = System.nanoTime();
        Arrays.stream(a).boxed().sorted((x,y)->x>y?-1:1).forEach(System.out::print);
        long endTime = System.nanoTime();
        long time1 = endTime - startTime;
        System.out.println("\n" + time1);
        startTime = System.nanoTime();
        Arrays.sort(a);
        for(int i = a.length - 1;i >= 0;i--){
            System.out.print(a[i]);
        }
        endTime = System.nanoTime();
        long time2 = endTime - startTime;
        System.out.println("\n" + time2);
        double diff =1.0* time1 / time2;
        System.out.println("倍数:" + diff);
    }
}
