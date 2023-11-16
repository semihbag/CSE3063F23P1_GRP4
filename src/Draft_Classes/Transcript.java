package Draft_Classes;

import java.util.ArrayList;

public class Transcript {
    private double GPA_100;
    private int year;
    private ArrayList<Course> passedCourses;
    private ArrayList<Course> failedCourses;

    public Transcript(double GPA_100, int year, ArrayList<Course> passedCourses, ArrayList<Course> failedCourses) {
        this.GPA_100 = GPA_100;
        this.year = year;
        this.passedCourses = passedCourses;
        this.failedCourses = failedCourses;
    }

    public void setPassedCourses(ArrayList<Course> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public double getGPA_100() {
        return GPA_100;
    }

    public void setGPA_100(double GPA_100) {
        this.GPA_100 = GPA_100;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Course> getPassedCourses() {
        return passedCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<Course> failedCourses) {
        this.failedCourses = failedCourses;
    }
}