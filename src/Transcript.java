import java.util.ArrayList;

public class Transcript {
    private double GPA_100;
    private int year;
    private int totalCredit; //
    private ArrayList<GradeClass> passedCourses, failedCourses;

    public Transcript(double GPA_100, int year, ArrayList<GradeClass> passedCourses, ArrayList<GradeClass> failedCourses) {
        this.GPA_100 = GPA_100;
        this.year = year;
        totalCredit = 0;
        this.passedCourses = passedCourses;
        this.failedCourses = failedCourses;

    }

    public void calculateTotalCredit() {
        for(int i = 0 ; i < passedCourses.size() ; i++) {
            totalCredit += passedCourses.get(i).getCredit(); //
        }
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

    public ArrayList<GradeClass> getPassedCourses() {
        return passedCourses;
    }

    public ArrayList<GradeClass> getFailedCourses() {
        return failedCourses;
    }

    public int getTotalCredit() { //
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) { //
        this.totalCredit = totalCredit;
    }
}
