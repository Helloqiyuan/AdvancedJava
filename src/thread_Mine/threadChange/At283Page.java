package thread_Mine.threadChange;

import java.util.Date;

public class At283Page {
    public static void main(String[] args) {
        Action s = new Action();
        Thread thread = new Thread(s);
        System.out.println("主线程执行...");
        System.out.println("主线程睡眠5s");
        thread.start();
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println("主线程被中断");
        }
        System.out.println("主线程继续执行...");
        thread.interrupt();
    }
}
class Action implements Runnable {
    public void run() {
        while(true){
            long a = 0L;
            try {
                for (long i = 0; i < 10000000000L; i++) {
                    if ((i & 1) == 1) {
                        a += 1;
                    } else {
                        a += 2;
                    }
                }
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("子线程被中断");
                break;
            }
            System.out.println(new Date());
        }
    }
}
