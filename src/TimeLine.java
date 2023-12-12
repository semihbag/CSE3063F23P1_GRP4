import java.util.ArrayList;

public class TimeLine {
    private Day day;
    private ArrayList<Hour> hour;

    public TimeLine(Day day, ArrayList<Hour> hour){
        this.setDay(day);
        this.setHour(hour);
    }
    public Day getDay() {
        return day;
    }

    public ArrayList<Hour> getHour() {
        return hour;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setHour(ArrayList<Hour> hour) {
        this.hour = hour;
    }
}
