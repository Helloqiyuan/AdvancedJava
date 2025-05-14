package meetingRoomReservationSystem;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    //会议室
    private MeetingRoom meetingRoom;
    //会议日期
    private LocalDate date;
    //会议开始时间
    private LocalTime startTime;
    //会议结束时间
    private LocalTime endTime;

    //构造函数
    public Reservation(MeetingRoom meetingRoom, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.meetingRoom = meetingRoom;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //get(),set(),toString()
    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public String toString() {
        return "预定时间{" +
                "会议室:" + meetingRoom +
                ", 开始时间=" + startTime +
                ", 结束时间=" + endTime +
                '}';
    }
}
