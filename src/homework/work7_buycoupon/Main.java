package homework.work7_buycoupon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception{

        // 创建线程池
        ExecutorService pool = new ThreadPoolExecutor(
                10,
                20,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        // 创建任务对象
        SalesCallable sc1 = new SalesCallable(100);
        int count = 1;
        //标记优惠券是否还有
        boolean flag = true;
        while(flag){
            try {
                Customer customer = new Customer("顾客" + (count++) + " ");
                sc1.setCustomer(customer);
                //将任务交给线程池处理
                Future<Boolean> f = pool.submit(sc1);
                //如果当前线程执行完成,则设置优惠券是否存在
                if(f.isDone()){
                    flag = f.get();
                }
                Thread.sleep(25);
            } catch (Exception e) {
                System.out.println("当前排队人数过多,请稍后重试");
                Thread.sleep(50);
            }
        }
        pool.shutdown();
    }
}
