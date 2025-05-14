package thread;

public class Thread01 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
    public static void main(String[] args) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(Thread.currentThread().getName() + "-->" + i);
//                }
//            }
//        });
        Thread t01 = new Thread(new Thread01());
        Thread t02 = new Thread(new Thread01());
        t01.setName("线程0");
        t02.setName("线程1");
        t01.setPriority(Thread.MIN_PRIORITY);
        t02.setPriority(Thread.MAX_PRIORITY);
        t01.start();
        t02.start();
    }
}
