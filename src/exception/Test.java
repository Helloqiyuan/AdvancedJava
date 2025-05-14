package exception;

public class Test {
    public int div(int x,int y) throws MyException{
        if(y==0){
            throw new MyException("除数不能为0");
        }
        return x/y;
    }
}
