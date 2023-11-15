package Draft_Classes;

import Draft_Classes.Course;

import java.util.ArrayList;

public class Lecturer extends Person {
    private Id id;

    private ArrayList<Course> givenCourses = new ArrayList<>();

    Lecturer(Id id, String name, String surname){
        super(name, surname);
        this.id= id;
    }

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public void setGivenCourses(ArrayList<Course> givenCourses) {
        this.givenCourses = givenCourses;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
