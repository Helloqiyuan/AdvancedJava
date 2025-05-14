package homework.work3_Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main {
    public static void main(String[] args) throws Exception{
        Cat cat = new Cat();
        Class cls = cat.getClass();

        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        System.out.println("查看name属性:" + field.get(cat));
        field.set(cat,"元宝猫");
        System.out.println("修改后的name属性:" + field.get(cat));

        Method method = cls.getDeclaredMethod("hi",int.class);
        method.setAccessible(true);
        System.out.println("查看方法名称:" + method.getName());
        System.out.println("查看方法修饰类型:" + method.getModifiers());
        System.out.print("查看方法参数:" );
        Parameter[] parameter =  method.getParameters();
        for(Parameter x:parameter){
            System.out.println(x);
        }
        System.out.print("执行方法:");
        method.invoke(cat,2);
    }
}
