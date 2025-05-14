package thread_Mine.threadChange;

import java.util.Scanner;

public class multiMultiThread {
    public static void main(String[] args) {
        Long num = 0L;
        Thread thread;
        new Thread(new stopall()).start();
        try{
            for(int i = 5;i >= 1;i--){
                Thread.sleep(1000);
                System.out.print(i);
            }
            System.out.println();
            //System.out.print("\n等待接收停止指令(Enter):");
        }catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            new Thread(new print()).start();
            num++;
            if(num % 10000 == 0) {
                System.out.println("已创建线程数:" + num);
            }
        }
    }
}
class stopall implements Runnable{
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("线程炸弹将在5s后开始 在任何时候输入回车即可强制停止程序");
        String a = scanner.nextLine();
        System.out.println("已停止");
        System.exit(0);
    }
}
class print implements Runnable{
//    public static Long biggestThread = 0L;
    public void run(){
        while(true){}
    }
}
