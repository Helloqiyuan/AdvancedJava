package thread_Mine.threadJoin;

public class Test {
    public static void main(String[] args) {
        Thread son = new Thread(new Action());
        son.start();
        for(int i = 0;i < 10;i++){
            System.out.println("main:" + i);
            if(i == 5){
                try{
                    son.join();
                }catch (InterruptedException e){
                    e.getMessage();
                }
            }
        }
        System.out.println("finally");
    }
}
class Action implements Runnable {
    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
                System.out.println("son...");
            }catch (InterruptedException e){
                e.getMessage();
            }
        }
    }
}
