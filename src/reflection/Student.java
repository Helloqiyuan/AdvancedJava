package reflection;

public class Student implements MyInterface{
    @Override
    public void interfaceMethod() {
        System.out.println("接口方法");
    }
    //成员变量id,name,age
    private int id;
    private String name;
    private int age;
    //成员方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    //构造方法

    public Student(){
        System.out.println("无参构造方法");
    }

    public Student(int id){
        this.id = id;
        System.out.println("id");
    }
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println("all");
    }
    @Override
    public String toString(){
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    //不带参数私有方法
    private void privateMethod(){
        System.out.println("不带参数的私有方法");
    }
    //带参数私有方法
    private void privateMethod2(String name){
        System.out.println("带参数的私有方法2" + name);
    }
    private void privateMethod3(int id){
        System.out.println("带参数的私有方法3" + id);
    }
}
