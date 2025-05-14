package homework.midexamination;

public class work1 {
    public static void main(String[] args) {
        CanRun canRun = new CanRun();
        Thread t1 = new Thread(canRun);
        Thread t2 = new Thread(canRun);
        t1.setName("1");
        t2.setName("2");
        t1.start();
        t2.start();
    }
}
class CanRun implements Runnable{
    int turn = 1;
    @Override
    public void run(){
        synchronized (this){
            for(int i = 1;i <= 5;i++) {
                while (Integer.parseInt(Thread.currentThread().getName()) != turn) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"正在运行" + i);
                turn = turn == 1 ? 2 : 1;
                this.notify();
            }
        }
    }
}
