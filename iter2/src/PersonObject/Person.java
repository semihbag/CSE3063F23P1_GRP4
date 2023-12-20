package PersonObject;

import java.util.ArrayList;
import CourseObject.*;

public abstract class Person {
    private String firstName, lastName;
    private Password password;
    private Syllabus syllabus;

    public Person(String firstName, String lastName, Password password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password=password;
        syllabus = new Syllabus();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public abstract void createSyllabus(ArrayList<Course> courses);

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }
}
