package homework.work7_buycoupon;

import java.util.concurrent.Callable;

public class SalesCallable implements Callable<Boolean> {
    private int coupon;
    private Customer customer;
    public SalesCallable(int coupon) {
        this.coupon = coupon;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public Boolean call() throws Exception {
        synchronized (this){
            if(coupon > 0){
                System.out.println(
                        customer.getName() +
                        "在" +
                        Thread.currentThread().getName() +
                        "抢到了优惠券,优惠券编号为:YH00" +
                        (coupon--)
                );
                Thread.sleep(50);
                return coupon > 0;
            }else{
                System.out.println(customer.getName() + "没有抢到优惠券");
                return false;
            }
        }
    }
}
