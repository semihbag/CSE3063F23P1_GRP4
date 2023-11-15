package Draft_Classes;

import java.util.ArrayList;

public class Course {
    private Lecturer lecturer;
    private Id courseId;
    private String courseName;
    private ArrayList<Id> prequisiteCourses = new ArrayList<>();
    private ArrayList<Student> studentList = new ArrayList<>();
    private String day_hour;
    private int quota;
    private int year;

    public Course(Id courseId, String courseName, int quota, int year, String day_hour,
                  Lecturer lecturer){
        this.courseId = courseId;
        this.quota = quota;
        this.courseName = courseName;
        this.year = year;
        this.day_hour = day_hour;
        this.lecturer = lecturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getDay_hour() {
        return day_hour;
    }

    public void setDay_hour(String day_hour) {
        this.day_hour = day_hour;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Id> getPrequisiteCourses() {
        return prequisiteCourses;
    }

    public void setPrequisiteCourses(ArrayList<Id> prequisiteCourses) {
        this.prequisiteCourses = prequisiteCourses;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Id getCourseID() {
        return courseId;
    }

    public void setCourseID(Id courseId) {
        this.courseId = courseId;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
