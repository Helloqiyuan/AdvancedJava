package thread.lock;

import static java.lang.Thread.sleep;

public class TestLockBank {
    public static void main(String args[])
    {
        LockBank bank = new LockBank(10);
        Thread accountant,    //会计
                cashier;      //出纳
        accountant = new Thread(bank);
        cashier = new Thread(bank);
        accountant.setName("存钱");
        cashier.setName("取钱");
        cashier.start();    //启动线程
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        accountant.start(); //启动线程
    }
}
