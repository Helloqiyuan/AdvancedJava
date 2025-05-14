package homework.work5_multiThread;

public class Main {
    public static int aa = 0;
    public static int bb = 0;
    public static int cc = 0;
    public static void main(String[] args) {
        Thread x = new Thread(new MyRun());
        Thread a = new Thread(x,"售票员A");
        Thread b = new Thread(x,"售票员B");
        Thread c = new Thread(x,"售票员C");
        a.start();
        b.start();
        c.start();
        while(a.isAlive() || b.isAlive() || c.isAlive()){}
        System.out.println("售票员A卖了:" + aa + ",售票员B卖了:" + bb + ",售票员C卖了:" + cc);
    }
}
class MyRun implements Runnable{
    private int piao = 100;
    @Override
    public void run() {
        while(true){
            synchronized (this) {
                if(piao <= 0){
                    return;
                }
                String name = Thread.currentThread().getName();
                if (name.equals("售票员A")) {
                    Main.aa ++;
                } else if (name.equals("售票员B")) {
                    Main.bb ++;
                } else if (name.equals("售票员C")) {
                    Main.cc ++;
                }
                System.out.println(name + "正在卖第" + piao-- + "张票");
            }
        }
    }
}
