package PersonObject;

import java.util.ArrayList;
import CourseObject.*;

public abstract class Person {
    private String firstName, lastName;
    private Id personId;
    private Password password;
    private Syllabus syllabus;

    public Person(String firstName, String lastName, Id personId,Password password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId= personId;
        this.password=password;
        syllabus = new Syllabus();
    }

    public boolean equals(Object o){
        if(o instanceof Person){
            return ((Person) o).getPersonId().getId().equals(this.getPersonId().getId());
        }
        return false;
    }

    public Id getPersonId() {return personId;}

    public void setPersonId(Id personId) {this.personId = personId;}

    public Person() {
    }

    public abstract Person login(String[] userInfo, ArrayList<Person> persons);

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
