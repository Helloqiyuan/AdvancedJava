package jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    //  数据库驱动
    static String jdbc_driver =
            "com.mysql.cj.jdbc.Driver";
    // 数据库连接
    static String DB_URL =
            "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC";
    static String USER = "root";
    static String PASS = "QQ666666";
    // 查询
    public static void query(){

        try{
            //1.导入驱动包
            Class.forName(jdbc_driver);
            System.out.println("数据库驱动加载成功");

            //2.获取数据库连接
            Connection conn =
                    java.sql.DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("数据库连接成功");
            //3.创建数据库操作对象
            Statement stmt = conn.createStatement();
            //4.执行SQL语句
            String sql = "select * from a2353";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("id") + " " + rs.getString("name") +  " " + rs.getString("age") + " " + rs.getString("class"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        query();
    }
}
