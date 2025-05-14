package voteSystem;

import java.io.*;
import java.util.*;

public class Test {
    public static Scanner sc = new Scanner(System.in);
    //当前是否登录
    public static boolean logIn = false;
    //当前用户
    public static User user;
    //文件路径
    public static File userFile = new File("src/VoteSystem/Files/Users.txt");
    public static File campaignerFile = new File("src/VoteSystem/Files/Campaigner.txt");
    public static File ResultFile = new File("src/VoteSystem/Files/Result.txt");
    //存储用户信息
    public static HashSet<User> userData = new HashSet<>();
    //存储参选者信息
    public static LinkedHashSet<Campaigner> campaignerData = new LinkedHashSet<>();
    public static StringBuilder Result = new StringBuilder();
    public static void main(String[] args) {
        menu();
        //从文件读取用户信息
        ReadFile(userData,userFile);
        //从文件读取参选者信息
        ReadFile(campaignerData,campaignerFile);
        while(true){
            System.out.print("请选择操作:");
            switch (sc.nextInt()){
                case 1:
                    UserSignUp();
                    break;
                case 2:
                    UserLogIn();
                    break;
                case 3:
                    CheckCampaigner();
                    break;
                case 4:
                    Vote();
                    break;
                case 5:
                    EndVote(0);
                    break;
                case 0:
                    EndVote(1);
                    System.out.println("系统已退出");
                    System.exit(0);
            }
        }
    }
    public static void menu(){
        System.out.println("-----欢迎进入投票系统-----");
        System.out.println("1.用户注册");
        System.out.println("2.用户登录");
        System.out.println("3.查看竞选人");
        System.out.println("4.投票");
        System.out.println("5.结束投票");
        System.out.println("0.退出程序");
    }
    public static void UserSignUp(){
        //当前正在注册的用户
        user = InputUser();
        //判断文件中是否有该用户的注册信息
        if(userData.contains(user)){
            System.out.println("用户" + user.getName() + "已存在,请直接登录!");
        }else{
            userData.add(user);
            System.out.println("用户" + user.getName() + "注册成功!");
        }
        user = null;
    }
    public static void UserLogIn(){
        if(logIn){
            System.out.println("用户" + user.getName() + "已安全退出");
        }
        logIn = false;
        //当前尝试登录的用户的信息
        user = InputUser();
        if(userData.contains(user)){
            logIn = true;
            for(User x:userData){
                if(user.equals(x)){
                    user.setVoteNumbers(x.getVoteNumbers());
                    break;
                }
            }
            System.out.println("用户" + user.getName() + "登录成功,欢迎使用!");
        }else{
            System.out.println("用户" + user.getName() + "登录失败,可能是用户未注册或密码错误");
        }
    }
    public static void CheckCampaigner(){
        System.out.println("候选人信息如下");
        System.out.println("姓名\t\t学分\t\t竞选宣言");
        for(Campaigner x: campaignerData){
            System.out.println(x);
        }
    }
    public static void Vote(){
        if(!logIn){
            System.out.println("请先登录后再进行投票操作!!!");
            return ;
        }
        if(user.getVoteNumbers() == 0){
            System.out.println("投票次数已用尽!");
            return ;
        }
        CheckCampaigner();
        System.out.print("请输入你选择的投票的第1个竞选人:");
        String s1 = sc.next();
        System.out.print("请输入你选择的投票的第2个竞选人:");
        String s2 = sc.next();
        if(s2.equals(s1)){
            System.out.println("不能给同一个人投两次票!!!");
            System.out.print("请输入你选择的投票的第2个竞选人:");
            s2 = sc.next();
        }
        user.setVoteNumbers(0);
        userData.remove(user);
        userData.add(user);
        for(Campaigner x:campaignerData){
            if(x.getName().equals(s1) || x.getName().equals(s2)){
                x.incrementVote();
            }
        }
        System.out.println("投票成功!");
    }
    public static void EndVote(int k){
        if(!logIn && k == 0){
            System.out.println("都没登录你结束啥?");
            return;
        }
        //写入用户信息
        WriteFile(userFile,userData);
        //写入结果
        ArrayList<Campaigner> toSort = new ArrayList<>(campaignerData.stream().toList());
        toSort.sort((a,b)->b.getVoteCount() - a.getVoteCount());
        //
        Result.append("所有竞选人票数如下:\n");
        Result.append("姓名\t\t票数\n");
        for(Campaigner x:campaignerData){
            Result.append(x.getName() + "\t\t" + x.getVoteCount() + "\n");
        }
        Result.append("\n入选人员如下:\n");
        Result.append(toSort.get(0).getName() + "\t\t" + toSort.get(0).getVoteCount() + "\n");
        Result.append(toSort.get(1).getName() + "\t\t" + toSort.get(1).getVoteCount());
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ResultFile));
            writer.write(Result.toString());
            writer.close();
            if(k == 0){
                System.out.println("投票结束,请在文件Result.txt中查看结果。");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        logIn = false;
        user = null;
    }
    public static User InputUser(){
        System.out.print("请输入您的姓名:");
        String name = sc.next();
        System.out.print("请输入您的学号:");
        String ID = sc.next();
        System.out.print("请输入您的密码:");
        String password = sc.next();
        User user = new User(name,ID,password);
        return user;
    }
    public static <T> void ReadFile(HashSet<T> data,File file){
        if(!file.exists() || file.length() == 0){
            return ;
        }
        try{
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream OIS = new ObjectInputStream(input);
            data.clear();
            data.addAll((HashSet<T>) OIS.readObject());
            OIS.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static <T> void WriteFile(File file,HashSet<T> data){
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream OOS = new ObjectOutputStream(output);
            OOS.writeObject(data);
            OOS.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}