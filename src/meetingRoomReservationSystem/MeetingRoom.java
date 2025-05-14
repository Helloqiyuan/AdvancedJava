package meetingRoomReservationSystem;

class MeetingRoom {
    private String name;
    private int volume;
    private boolean tool;

    public MeetingRoom(String name, int volume, boolean tool) {
        this.name = name;
        this.volume = volume;
        this.tool = tool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean getTool() {
        return tool;
    }

    public void setTool(boolean tool) {
        this.tool = tool;
    }

    public String toString() {
        return this.getName() + "     " + this.getVolume() + "\t  " + (this.getTool() ? "有" : "无");
    }
}
