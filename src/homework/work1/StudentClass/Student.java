package homework.work1.StudentClass;

public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String toString(){
        return "姓名:" + this.getName() + "年龄:" + this.getAge();
    }
    public int hashCode(){
        return this.getName().hashCode() + Integer.hashCode(this.getAge());
    }
    public boolean equals(Object x){
        Student x1 = (Student) x;
        return (this.getName().equals(x1.getName()) && this.getAge() == x1.getAge());
    }
}
