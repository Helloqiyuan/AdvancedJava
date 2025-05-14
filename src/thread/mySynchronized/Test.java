package thread.mySynchronized;

public class Test extends Thread{
    private int piao = 10;
    @Override
    public  void run(){
        synchronized(this) {
            while (piao > 0) {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                {
                    System.out.println(Thread.currentThread().getName() + "-->" + piao--);
                }
            }
        }
    }
}
class A{
    public static void main(String[] args) {
        Thread a = new Test();
        Thread x = new Thread(a);
        Thread y = new Thread(a);
        x.start();
        y.start();
    }
}
