package thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank implements Runnable{
    private int money;

    public LockBank(int money){
        this.money = money;
    }
    private void saveOrTake(int amount){
        //定义锁
        Lock lock = new ReentrantLock();//创建锁对象
        try{
            lock.lock();//上锁
            if(Thread.currentThread().getName().equals("存钱")){
                for(int i = 0; i < 5; i++)
                {
                    money = money +amount/5;
                    System.out.println(Thread.currentThread().getName()+"存了"+amount/5+"万元，余额为"+money+
                            "万元，休息一会继续存");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notify();
            }
            else if(Thread.currentThread().getName().equals("取钱")){
                if(money < 200){
                    System.out.println("wait...");
                    wait();
                }
                for(int i = 0; i < 5; i++)
                {
                    money =money -amount/5;
                    System.out.println(Thread.currentThread().getName()+"取了"+amount/5+"万元，余额为"+money+
                            "万元，休息一会继续取");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            if (Thread.currentThread().getName().equals("存钱")) {
                saveOrTake(500);

            } else if (Thread.currentThread().getName().equals("取钱")) {
                saveOrTake(200);
            }
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
