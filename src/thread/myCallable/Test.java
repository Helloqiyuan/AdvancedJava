package thread.myCallable;

import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) throws Exception {
        FutureTask<String> task = new FutureTask<>(new MyCallable());
        FutureTask<String> task2 = new FutureTask<>(new MyCallable());
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        System.out.println(task.get());
        System.out.println(task2.get());
    }
}

