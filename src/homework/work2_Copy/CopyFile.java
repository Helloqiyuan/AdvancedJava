package homework.work2_Copy;

import java.io.*;
import java.util.*;

public class CopyFile {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print("输入源目录地址:");
        File origin = new File(sc.nextLine());
        File dest = new File("D:\\destination");
        int[] count = new int[1];
        copy(origin,dest,count);
        System.out.println(count[0]);
    }
    public static void copy(File origin,File dest,int[] count) throws Exception{
        dest.mkdir();
        File[] file = origin.listFiles();
        for(File x:file){
            if(x.isFile() && x.getName().endsWith(".java")){
                //init
                File des;
                HashSet<String> set = new HashSet<>();
                for(File y:dest.listFiles()){
                    set.add(y.getName());
                }
                des = new File(dest,realName(set,x));
                //c->v
                BufferedReader reader = new BufferedReader(new FileReader(x));
                BufferedWriter writer = new BufferedWriter(new FileWriter(des));
                String line;
                while((line = reader.readLine()) != null){
                    writer.write(line);
                }
                count[0]++;
                writer.close();
                reader.close();
            }else if(x.isDirectory()){
                copy(x,new File(dest,x.getName()),count);
            }
        }
    }
    public static String realName(HashSet<String> set,File x){
        if(!set.contains(x.getName())){
            return x.getName();
        }
        return realName(set,new File(x.getParent(),x.getName().replace(".java","") + "-副本.java"));
    }
}
