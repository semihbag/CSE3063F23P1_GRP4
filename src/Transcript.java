import java.util.ArrayList;

public class Transcript {
    private double GPA_100;
    private int year;
    private ArrayList<ID> passedCourses;
    private ArrayList<ID> failedCourses;

    public Transcript(double GPA_100, int year, ArrayList<ID> passedCourses, ArrayList<ID> failedCourses) {
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

    public ArrayList<ID> getPassedCourses() {
        return passedCourses;
    }

    public void setPassedCourses(ArrayList<ID> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public ArrayList<ID> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<ID> failedCourses) {
        this.failedCourses = failedCourses;
    }
}