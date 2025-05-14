package homework.work4_Json;

import java.io.ObjectStreamException;
import java.lang.reflect.Field;

public class ObjectMapper {
    public static String toJson(Object object) throws IllegalArgumentException, IllegalAccessException{
        //获取对象对用的Class对象
        Class cls = object.getClass();
        StringBuilder json = new StringBuilder();
        json.append("{");
        //获取所有的属性
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            json.append("\"" + fields[i].getName() + "\":");
            if(fields[i].getType() == String.class){
                json.append("\"" + fields[i].get(object) + "\"");
            }else{
                json.append(fields[i].get(object));
            }
            if(i != fields.length - 1){
                json.append(",");
            }
        }
        json.append("}");
        return json.toString();
    }
    public static <T> T fromJson(String json,Class<T> clazz) throws Exception{
        if(json == null || json.isEmpty()){
            return null;
        }
        //根据对象的空参构造对象的实例对象newInstance
        T x = clazz.getConstructor().newInstance();
        //获取所有的属性和对应属性值
        String[] each = json.substring(1,json.length() - 1).split(",");
        for(String s:each) {
            String[] pair = s.split(":");
            String name = pair[0].replaceAll("\"", "");
            String value = pair[1].replaceAll("\"", "");
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            if (field.getType() == String.class) {
                field.set(x, value);
            } else if (field.getType() == int.class) {
                field.set(x, Integer.parseInt(value));
            } else if (field.getType() == double.class) {
                field.set(x, Double.parseDouble(value));
            } else if (field.getType() == boolean.class) {
                field.set(x, Boolean.parseBoolean(value));
            }

        }
        return x;
    }
}

