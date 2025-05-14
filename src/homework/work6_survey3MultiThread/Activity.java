package homework.work6_survey3MultiThread;

public class Activity {
    private int coupon;
    public Activity(int coupon){
        this.coupon = coupon;
    }
    public int getCoupon(){
        return coupon;
    }
    public void saleCoupon(){
        if(coupon > 0){
            System.out.println(Thread.currentThread().getName() + "买到了");
            coupon --;
        }
    }
}
