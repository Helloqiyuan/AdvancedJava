package file;
import java.io.*;
import java.util.*;
public class Test {
    public static Scanner scan = new Scanner(System.in);
    public static File diary;
    public static void main(String[] args) {
        menu();
        while(true){
            System.out.print("选择:");
            switch (scan.nextInt()){
                case 1:
                    writeDiary();
                    break;
                case 2:
                    fixDiary();
                    break;
                case 3:
                    viewDiary();
                    break;
                case 4:
                    deleteDiary();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
    public static void menu(){
        System.out.println("1.写日记");
        System.out.println("2.修改日记");
        System.out.println("3.查看日记");
        System.out.println("4.删除日记");
        System.out.println("5.退出");
    }
    public static void writeDiary(){
        System.out.print("输入日记名称(不包括后缀名):");
        scan.nextLine();//\n
        String name = scan.nextLine();
        diary = new File("D:/Diary/" + name + ".txt");
        if(!diary.exists()){
            try{
                diary.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //日记存在,有内容的操作
        if(diary.length() > 0){
            System.out.println("日记本已有内容,请选择操作");
            System.out.println("1.覆盖内容\n2.追加内容\n3.取消操作");
            System.out.print(":");
            String input;
            StringBuilder content = new StringBuilder();
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    System.out.println("请输入要写入日记的内容(stop结束)");
                    System.out.print(":");
                    scan.nextLine();
                    while(!(input = scan.nextLine()).equals("stop")){
                        content.append(input);
                    }
                    try{
                        Writer overWriter = new FileWriter(diary);
                        overWriter.write(content.toString());
                        overWriter.flush();
                        overWriter.close();
                        System.out.println("已覆盖内容");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("输入要追加的内容(stop结束)");
                    System.out.print(":");
                    scan.nextLine();
                    while(!(input = scan.nextLine()).equals("stop")){
                        content.append(input);
                    }
                    try {
                        Writer appendWriter = new FileWriter(diary,true);
                        appendWriter.write(content.toString());
                        appendWriter.flush();
                        appendWriter.close();
                        System.out.println("已追加内容");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("已取消操作");
                    break;
            }
        }else{//日记本没有内容
            System.out.println("请输入要写入日记的内容(stop结束)");
            System.out.print(":");
            StringBuilder content = new StringBuilder();
            String input;
            while(!(input = scan.nextLine()).equals("stop")){
                content.append(input);
            }
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(diary));
                writer.write(content.toString());
                writer.flush();
                writer.close();
                System.out.println("已写入内容");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void fixDiary(){
        System.out.println("输入要修改的日记本");
        System.out.print(":");
        scan.nextLine();//\n
        String name = scan.nextLine();

        String path = "D:/diary/" + name + ".txt";
        diary = new File(path);
        //存储日记内容
        StringBuilder fiaryContent = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(diary));
            String line;
            while((line = reader.readLine()) != null){
                fiaryContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        existAndPrintDiary(diary);
        System.out.println("请输入要修改的内容(格式:修改的目标文字&修改后的文字),输入stop结束");
        System.out.print(":");
        List<String> needFixWord = new ArrayList<>();
        String input;
        while(!(input = scan.nextLine()).equals("stop")){
            needFixWord.add(input);
        }
        //修改日记
        for(String i:needFixWord){
            String[] c = i.split("&");
            int start = fiaryContent.indexOf(c[0]);
            fiaryContent.replace(start,start + c[0].length(),c[1]);
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(diary));
            writer.write(fiaryContent.toString());
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("日记修改成功!");
    }
    public static void viewDiary(){
        System.out.print("请输入要查看的日记本");
        System.out.print(":");
        scan.nextLine();
        String name = scan.nextLine();
        diary = new File("D:/Diary/" + name + ".txt");
        existAndPrintDiary(diary);
    }
    public static void existAndPrintDiary(File file){
        if(!file.exists()){
            System.out.println("日记不存在!!!");
            return ;
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            System.out.println("日记的内容是:");
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void deleteDiary(){
        System.out.print("请输入要删除的日记本");
        System.out.print(":");
        scan.nextLine();
        String name = scan.nextLine();
        diary = new File("D:/Diary/" + name + ".txt");
        if(!diary.exists()){
            System.out.println("未找到:" + diary.getName());
        }
        //existAndPrintDiary(diary);
        diary.delete();
        if(!diary.exists()){
            System.out.println("日记已删除!!!");
        }
    }
}
