package PersonObject;

import CourseObject.*;
import java.util.ArrayList;

public class Lecturer extends Person {
    private ArrayList<Course> givenCourses = new ArrayList<>();
    private Course selectedCourse;

    public Lecturer(String FirstName, String LastName, Id lecturerId, Password password) {
        super(FirstName, LastName,lecturerId,password);
    }

    public Lecturer() {
    }

    @Override
    public Person login(String[] userInfo, ArrayList<Person> persons) {
        String username = userInfo[0];
        String password = userInfo[1];
        for (int i = 0; i < persons.size(); i++) {
            Lecturer lecturer = (Lecturer) persons.get(i);
            if (("l" + lecturer.getPersonId().getId()).equals(username) &&
                    lecturer.getPassword().getPassword().equals(password)) {
                lecturer.createSyllabus(lecturer.getGivenCourses());
                return lecturer;
            }
        }
        return null;
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