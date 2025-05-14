package thread;

public class TestJoinThread implements Runnable{
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }

    public static void main(String[] args){
        TestJoinThread ran = new TestJoinThread();
        Thread t1 = new Thread(ran);
        t1.setName("02");
        t1.start();
        //主线程中强制执行子线程t1
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"-->"+i);
            if(i == 5){
                try{

                    t1.join();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
