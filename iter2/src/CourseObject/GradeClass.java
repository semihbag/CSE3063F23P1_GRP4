package CourseObject;

public class GradeClass {
    private Course course;
    private Grade grade;
    private int term;

    
    public GradeClass(Course course, Grade grade){
        this.setCourse(course);
        this.setGrade(grade);
    }

    public Course getCourse() {
        return course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public void setTerm(int term) {
        this.term = term;
    }
    public int getTerm() {
        return term;
    }
}
