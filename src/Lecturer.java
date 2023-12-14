import java.util.ArrayList;

public class Lecturer extends Person{
    private Id lecturerId;
    private ArrayList<Course> givenCourses = new ArrayList<>();
    private Course selectedCourse;

    public Lecturer(String FirstName, String LastName, Id lecturerId,Password password) {
        super(FirstName, LastName,password);
        this.lecturerId = lecturerId;
    }
    public void selectCourse(int index) {
        index = index - 1;
        Course currentCourse = givenCourses.get(index);
        this.setSelectedCourse(currentCourse);
    }

    @Override
    public void createSyllabus(ArrayList<Course> givenCourses) {
        getSyllabus().fillSyllabus(givenCourses);
    }

    public Id getLecturerId() {
        return lecturerId;
    }

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(ArrayList<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }
    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}