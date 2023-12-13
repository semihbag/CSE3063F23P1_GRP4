package SourceCodes;

import java.util.ArrayList;

public class CourseSchedule {

    private Day courseDay;
    private ArrayList<Hour> courseHours;

    public CourseSchedule(Day courseDays,ArrayList<Hour> courseHours) {
        this.courseDay = courseDays;
        this.courseHours = courseHours;
    }

    public Day getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(Day courseDay) {
        this.courseDay = courseDay;
    }

    public ArrayList<Hour> getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(ArrayList<Hour> courseHours) {
        this.courseHours = courseHours;
    }

    
}

