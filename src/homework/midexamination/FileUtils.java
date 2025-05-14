package homework.midexamination;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void copyTestFile(String src,String dest) throws IOException {
        File origin = new File(src);
        File destination = new File(dest);
        if(!origin.exists()){
            System.out.println("源文件不存在");
            return;
        }
        if (!destination.exists()){
            destination.mkdir();
        }
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(origin));
        while((line = reader.readLine()) != null){
            sb.append(line);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
        writer.write(sb.toString());
        reader.close();
        writer.close();
    }
    public static List<String> readLines(String filepath) throws IOException{
        List<String> ret = new ArrayList<>();
        File file = new File(filepath);
        if(!file.exists()){
            return ret;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while((line = reader.readLine()) != null){
            ret.add(line);
        }
        reader.close();
        return ret;
    }
}
