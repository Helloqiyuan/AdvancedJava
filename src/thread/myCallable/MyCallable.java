package thread.myCallable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        while(true){
            String str = Thread.currentThread().getName() + ":cal方法在运行";
            System.out.println(str);
            Thread.sleep(1000);
            return str;
        }
    }
}
