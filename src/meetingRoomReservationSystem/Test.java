package meetingRoomReservationSystem;

import java.util.*;

public class Test {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        List<MeetingRoom> ls = new ArrayList<>();
        ls.add(new MeetingRoom("会议室A102", 20, true));
        ls.add(new MeetingRoom("会议室A305", 15, true));
        ls.add(new MeetingRoom("会议室B201", 50, true));
        ls.add(new MeetingRoom("会议室B303", 10, false));
        ls.add(new MeetingRoom("会议室C101", 250, false));

        ReservationSystem reservationSystem = new ReservationSystem(ls);
//        reservationSystem.add(ls.get(0),"2025-02-26","08:00","09:35");
//        reservationSystem.add(ls.get(1),"2000-01-01","09:00","10:00");
//        reservationSystem.add(ls.get(4),"2025-02-26","10:05","11:40");
        menu();
        while(true){
            System.out.print("请输入您的选择:");
            int option = scan.nextInt();
            switch (option){
                case 1:
                    //查询所有会议室
                    reservationSystem.showAllMeetingRooms();
                    break;
                case 2:
                    reservationSystem.reserveMeetingRoom();
                    break;
                case 3:
                    reservationSystem.showResInfo();
                    break;
                case 4:
                    reservationSystem.selectRoomByTime();
                    break;
                case 5:
                    //系统退出
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
    }
    //菜单列表
    public static void menu(){
        System.out.println("------会议室预订系统------");
        System.out.println("1.查询所有会议室");
        System.out.println("2.预定会议室");
        System.out.println("3.查看预定信息");
        System.out.println("4.查看预定时间可用会议室");
        System.out.println("5.退出系统");
    }
}
