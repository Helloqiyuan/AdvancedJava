package thread_Mine.multiThread;

public class Action implements Runnable{
    public String x;
    Action(String x){
        this.x = x;
    }
    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            System.out.println(x);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}