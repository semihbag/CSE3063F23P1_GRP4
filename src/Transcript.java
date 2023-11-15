import java.util.ArrayList;

public class Transcript {
    private double GPA_100;
    private int year;
    private ArrayList<id> passedCourses;
    private ArrayList<id> failedCourses;

    public Transcript(double GPA_100, int year, ArrayList<id> passedCourses, ArrayList<id> failedCourses) {
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

    public ArrayList<id> getPassedCourses() {
        return passedCourses;
    }

    public void setPassedCourses(ArrayList<id> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public ArrayList<id> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<id> failedCourses) {
        this.failedCourses = failedCourses;
    }
}