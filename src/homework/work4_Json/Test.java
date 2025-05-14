package homework.work4_Json;
import reflection.Student;

public class Test {
    public static void main(String[] args) throws Exception{
        String x = ObjectMapper.toJson(new Space(1,"九院一号"));
        System.out.println(x);
        Space y = ObjectMapper.fromJson(x,Space.class);
        System.out.println(y);
        Student z = new Student(1,"张三",18);
        String a = ObjectMapper.toJson(z);
        System.out.println(a);
        Student b = ObjectMapper.fromJson(a,Student.class);
        System.out.println(b);
    }
}
