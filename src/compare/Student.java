package compare;

public class Student{
    private int stuNum;
    private String stuName;

    public Student() {
    }
    public Student(int stuNum, String stuName) {
        this.stuNum = stuNum;
        this.stuName = stuName;
    }
    public <T> void fun1(T t){

    }
    public void fun2(Object t){

    }
//    @Override
//    //内部比较
//    public int compareTo(Object o) {
//        Student input = (Student) o;
//        if(this.getStuNum() > input.getStuNum()){
//            return 1;
//        }else if(this.getStuNum() < input.getStuNum()){
//            return -1;
//        }
//        return 0;
//    }

    public String toString() {
        return "Student{" +
                "stuNum=" + stuNum +
                ", stuName='" + stuName + '\'' +
                '}';
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}
