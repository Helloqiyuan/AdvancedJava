package exception;

public class MyMath {
    public static void main(String[] args) {
        try{
            Test test = new Test();
            System.out.println(test.div(1,0));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("finally");
        }
    }
}
