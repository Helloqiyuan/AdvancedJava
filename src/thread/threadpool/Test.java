package thread.threadpool;

import java.util.AbstractQueue;
import java.util.concurrent.*;

class MyRunnable implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行任务" + i);
        }
    }
}
public class Test {
    public static void main(String[] args) {
//        public ThreadPoolExecutor(
//        int corePoolSize,     // 核心线程数（长期保留的线程）
//        int maximumPoolSize,  // 最大线程数（临时线程 = max - core）
//        long keepAliveTime,   // 临时线程空闲存活时间
//        TimeUnit unit,        // 存活时间单位
//        BlockingQueue<Runnable> workQueue, // 任务队列
//        ThreadFactory threadFactory, // 线程工厂
//        RejectedExecutionHandler handler)   // 拒绝策略
//
        //创建线程池对象
        ExecutorService pool = new ThreadPoolExecutor(
                4,
                10,
                6,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //创建线程任务,交由线程池处理
        Runnable target = new MyRunnable();
        for(int i = 0;i < 7;i++){
            //执行任务
            pool.execute(target);
        }
    }
}
