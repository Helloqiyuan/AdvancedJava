package file;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
public class Buffer {
    public static void main(String[] args) {
        System.out.println("欢迎进入签到管理系统!");
        String qdFilePath = "src/File/BufferFile/qdData.txt";
        String cdFilePath = "src/File/BufferFile/cdData.txt";
        File qdFile = new File(qdFilePath);
        File cdFile = new File(cdFilePath);
        if(!qdFile.exists()){
            try{
                qdFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(!cdFile.exists()){
            try{
                cdFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //读取签到数据
        try{
            BufferedReader readerQd = new BufferedReader(new FileReader(qdFile));
            BufferedWriter writerCd = new BufferedWriter(new FileWriter(cdFile));
            //定义一些规则
            //yyyy-MM-dd HH:mm:ss
            DateTimeFormatter fm = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.of(9,0,0);
            //读签到数据
            String oneLine;
            //美衣阁诗人
            while((oneLine = readerQd.readLine()) != null){
                String[] data = oneLine.replaceAll("\s+"," ").split(" ");
//                for(String x:data){
//                    System.out.print(x);
//                }
//                System.out.println();
                LocalTime tpTime = LocalTime.parse(data[2],fm);
                if(tpTime.isAfter(time)){
                    writerCd.write(oneLine);
                    writerCd.newLine();
                }
            }
            readerQd.close();
            writerCd.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.print("文件读取完毕!");
        }

    }
}
