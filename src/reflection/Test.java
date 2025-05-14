package reflection;
import java.io.FileReader;
import java.lang.reflect.*;
import java.util.*;
public class Test {
    private static void Demo01() {
        //1 获取反射入口
        try {
            //方法1 class.forName(String)
            Student s1 = new Student();
            Class<?> clazz1 = null;
            clazz1 = Class.forName("reflection.Student");
            System.out.println(clazz1);

            //方法2
            Class<Student> clazz2 = Student.class;
            System.out.println(clazz2);

            //方法3
            Student s2 = new Student();
            Class<?> clazz3 = s2.getClass();
            System.out.println(clazz3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private static void Demo02() {
        //2 获取方法
        try{
            //获取public方法
            Class<?> clazz1 = null;
            clazz1 = Class.forName("reflection.Student");
            Method[] methods1 =  clazz1.getMethods();
            for(Method method : methods1){
                System.out.println(method);
            }
            System.out.println("==========");
            //获取所有方法,包括私有方法,不包括父类
            Method[] methods2 = clazz1.getDeclaredMethods();
            for(Method method : methods2){
                System.out.println(method);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private static void Demo03() {
        //获取属性 getField() getFields() getDeclaredFields()
        try{
            Class<?> clazz1 = null;
            clazz1 = Class.forName("reflection.Student");
            //System.out.println("id:" + clazz1.getField("id"));
            //System.out.println("=============");
            System.out.println("name:" + clazz1.getDeclaredField("name"));
            System.out.println("=============");
            Field[] fields1 = clazz1.getFields();
            for(Field field : fields1){
                System.out.println(field);
            }
            System.out.println("============");
            Field[] fields2 = clazz1.getDeclaredFields();
            for(Field field : fields2){
                System.out.println(field);
            }
        }catch (ClassNotFoundException | NoSuchFieldException e){
            e.printStackTrace();
        }
    }
    private static void Demo04() {
        //获取接口 getInterfaces()
        try{
            Class<?> clazz1 = null;
            clazz1 = Class.forName("reflection.Student");
            System.out.println("接口:" + Arrays.toString(clazz1.getInterfaces()));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void Demo05() {
        //创建新实例
        try{
            Class<?> clazz1 = null;
            clazz1 = Class.forName("reflection.Student");
            Object obj = clazz1.newInstance();
            Student s1 = (Student) obj;
            s1.setId(10);
            s1.setName("张三");
            s1.setAge(20);

            System.out.println(s1);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
    public static void Demo06() {
        //构造方法
        Class<?> stuClass = null;
        try {
            stuClass = Class.forName("reflection.Student");
            //获取对象实例
            Student stu = (Student) stuClass.newInstance();
            Field idFiled = stuClass.getDeclaredField("id");
            //修改属性
            idFiled.setAccessible(true);
            idFiled.set(stu,20);
            System.out.println(stu.getId());

            System.out.println("=====构造方法======");
            //方法 有参数、无参数
            //构造方法
            Constructor <?> constructor = stuClass.getConstructor(int.class,String.class,int.class);
            Student stu1 = (Student) constructor.newInstance(20,"张三丰",18);
            System.out.println(stu1.toString());

            Constructor <?> constructor1 = stuClass.getConstructor(int.class);
            Student stu2 = (Student) constructor1.newInstance(50);
            System.out.println(stu2.toString());

            System.out.println("=====privateMethod======");
            //操作方法 无参数
            Method method = stuClass.getDeclaredMethod("privateMethod",null);
            method.setAccessible(true);
            method.invoke(stu,null);

            System.out.println("=====privateMethod2======");
            //操作方法 有参数
            Method method1 = stuClass.getDeclaredMethod("privateMethod2",String.class);
            method1.setAccessible(true);
            method1.invoke(stu,"张三");

            System.out.println("=====privateMethod3======");
            Properties prop = new Properties();
            prop.load(new FileReader("src/Reflection"));
            String className = prop.getProperty("className");
            String methodName = prop.getProperty("methodName");

            Method method2 = stuClass.getDeclaredMethod(methodName);
            method2.setAccessible(true);
            method2.invoke(stu,20666);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Demo01();
        //Demo02();
        //Demo03();
        //Demo04();
        //Demo05();
        //Demo06();
        Class cls = null;
        Class ss = Class.class;
    }
}
