package meetingRoomReservationSystem;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class ReservationSystem {
    //会议室
    private List<MeetingRoom> meetingRooms;
    //预定信息
    private List<Reservation> reservations;
    //输入
    private Scanner scan = new Scanner(System.in);

    //构造器
    public ReservationSystem(List<MeetingRoom> meetingRooms){
        this.meetingRooms = meetingRooms;
        reservations = new ArrayList<>();
    }

    //4查看所有会议室,遍历会议室
    public void showAllMeetingRooms(){
        System.out.println("------所有会议室列表------");
        System.out.println("名称\t\t容纳人数\t多媒体设备");
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.toString());
        }
        
    }

    //5预定会议室
    public void reserveMeetingRoom() {
        //展示可用会议室
        for (int i = 0; i < meetingRooms.size(); i++) {
            MeetingRoom room = meetingRooms.get(i);
            System.out.println((i+1) + ". " + room.getName() +
                    " (容纳人数：" + room.getVolume() + ", 多媒体设备：" +
                    (room.getTool() ? "有" : "无") + ")");
        }

        //选择会议室
        System.out.print("请选择要预订的会议室：");
        int roomNo = scan.nextInt();
        MeetingRoom selectedRoom = meetingRooms.get(roomNo - 1);
        //选择预订日期
        System.out.print("您选择了" + selectedRoom.getName() + ",");
        System.out.print("请选择预订日期 (yyyy-mm-dd):");
        //:接收预定日期字符串
        String dateStr = scan.next();
        //:解析日期时间字符串的对象
        DateTimeFormatter formatter;
        //:解析日期
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //:把日期字符串dateStr以formatter的格式转换成date对象
        LocalDate date = LocalDate.parse(dateStr,formatter);
        //选择预订时间
        System.out.print("请选择预订时间段 (例如：09:00-10:00):");
        String timeSlot = scan.next();
        String[] time = timeSlot.split("-");
        //解析时间
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        //开始时间
        LocalTime startTime = LocalTime.parse(time[0],formatter);
        //结束时间
        LocalTime endTime = LocalTime.parse(time[1],formatter);

        //判断该会议室该段时间是否被预订
        if(isAvailable(selectedRoom,date,startTime,endTime)){
            reservations.add(new Reservation(selectedRoom,date,startTime,endTime));
            System.out.println("预定成功!");
        }else{
            System.out.println("该时间段该会议室已被预订,请选择其他时间段或其他会议室!");
        }
    }

    //6判断会议室某时间段是否可用
    public boolean isAvailable(
            MeetingRoom selectedRoom,LocalDate date,LocalTime start,LocalTime end){
        if(reservations.isEmpty()){
            return true;
        }
        List<Reservation> list = reservations
                .stream()
                .filter(s -> s.getMeetingRoom() == selectedRoom)
                .filter(s -> s.getDate().equals(date))
                .filter(s -> !(s.getStartTime().isAfter(end) || s.getEndTime().isBefore(start)))
                .toList();
        return list.isEmpty();
//        if(reservations.isEmpty()){
//            return true;
//        }


//        for(Reservation reservation:reservations){
//            if(reservation.getMeetingRoom().getName().equals(selectedRoom.getName()) &&
//                    reservation.getDate().equals(date)){
//                if(!(reservation.getEndTime().isBefore(start) ||
//                        reservation.getStartTime().isAfter(end))){
//                    return false;
//                }
//            }
//        }
//        return true;
    }
    //7查看预定信息
    public void showResInfo(){
        System.out.println("--------------所有预订信息---------------");
        for(MeetingRoom meetingRoom:meetingRooms){
            System.out.println(meetingRoom.getName() + ":");
            boolean flag = true;
            for(Reservation reservation:reservations){
                if(reservation.getMeetingRoom().equals(meetingRoom)){
                    System.out.println("预定时间{" +
                            reservation.getDate() +
                            " " + reservation.getStartTime() +
                            "~" + reservation.getEndTime() +
                            "}");
                    flag = false;
                }
            }
            if(flag){
                System.out.println("该会议室暂无预订!");
            }
        }
    }
    //8查看指定时间段可用会议室
    public void selectRoomByTime(){
        System.out.print("请输入日期(yyyy-MM-dd):");
        String dateStr = scan.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr,formatter);
        System.out.print("请输入时间段(09:00-10:00)");
        String[] timStr = scan.next().split("-");
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(timStr[0],formatter);
        LocalTime end = LocalTime.parse(timStr[1],formatter);

        System.out.println("该时间段内可用会议室");
        System.out.println("名称\t\t容纳人数\t多媒体设备");
        Stream<MeetingRoom> st = meetingRooms.stream();
        st.filter(s->isAvailable(s,date,start,end)).forEach(System.out::println);
//        for(MeetingRoom meetingRoom:meetingRooms){
//            if(isAvailable(meetingRoom,date,start,end)){
//                System.out.println(meetingRoom.toString());
//            }
//        }
    }
    @Deprecated
    public void add(MeetingRoom meetingRoom, String date,String start,String end) {
        reservations.add(new Reservation(meetingRoom,
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm")),
                LocalTime.parse(end, DateTimeFormatter.ofPattern("HH:mm"))));
    }
}
