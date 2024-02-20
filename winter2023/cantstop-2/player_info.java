public class player_info {
    String name,color,level;
    boolean isComputerPlayer;
    public player_info(String name, String color, String level, boolean isComputerPlayer) {
        this.name = name;
        this.color = color;
        this.level = level;
        this.isComputerPlayer = isComputerPlayer;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public String getLevel() {
        return level;
    }
    public boolean isComputerPlayer() {
        return isComputerPlayer;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public void setisComputerPlayer(boolean isComputerPlayer) {
        this.isComputerPlayer = isComputerPlayer;
    }

   
}
