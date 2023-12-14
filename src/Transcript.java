import java.util.ArrayList;

public class Transcript {
    private double GPA_100;
    private int term;
    private int totalCredit; //
    private ArrayList<GradeClass> passedCourses, failedCourses;

    public Transcript(double GPA_100, int term, ArrayList<GradeClass> passedCourses, ArrayList<GradeClass> failedCourses) {
        this.GPA_100 = GPA_100;
        this.term = term;
        totalCredit = 0;
        this.passedCourses = passedCourses;
        this.failedCourses = failedCourses;
        this.totalCredit = calculateTotalCredit(); //*

    }

    public int calculateTotalCredit() { //*
        for(int i = 0 ; i < passedCourses.size() ; i++) {
            totalCredit += passedCourses.get(i).getCourse().getCredit(); //
        }
        return totalCredit; //*
    }

    public double getGPA_100() {
        return GPA_100;
    }

    public void setGPA_100(double GPA_100) {
        this.GPA_100 = GPA_100;
    }

    public int getTerm() {
        return term;
    }

    public void setYear(int term) {
        this.term = term;
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


