import java.util.ArrayList;

public class Transcript {
    private double GPA_100;
    private int year;
    private ArrayList<CourseID> passedCourses;
    private ArrayList<CourseID> failedCourses;

    public Transcript(double GPA_100, int year, ArrayList<CourseID> passedCourses, ArrayList<CourseID> failedCourses) {
        this.GPA_100 = GPA_100;
        this.year = year;
        this.passedCourses = passedCourses;
        this.failedCourses = failedCourses;
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

    public ArrayList<CourseID> getPassedCourses() {
        return passedCourses;
    }

    public void setPassedCourses(ArrayList<CourseID> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public ArrayList<CourseID> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<CourseID> failedCourses) {
        this.failedCourses = failedCourses;
    }
}