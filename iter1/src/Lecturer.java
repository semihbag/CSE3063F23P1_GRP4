import java.util.ArrayList;

public class Lecturer extends Person{

    private Id lecturerId;
    private ArrayList<Course> givenCourses = new ArrayList<>();

    public Lecturer(String FirstName, String LastName, Id lecturerId) {
        super(FirstName, LastName);
        this.lecturerId = lecturerId;
    }

    public Id getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId (Id id) {
        this.lecturerId = id;
    }

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(ArrayList<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }

}