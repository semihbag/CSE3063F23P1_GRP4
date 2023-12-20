package PersonObject;

import CourseObject.*;
import java.util.ArrayList;

public class Advisor extends Lecturer {
    private ArrayList<Student> students = new ArrayList<Student>(), awaitingStudents = new ArrayList<Student>();
    private Student selectStudent;

    // Constructor
    public Advisor(String FirstName, String LastName, Id lecturerID, Password password) {
<<<<<<<< HEAD:iter1/src/Advisor.java

        super(FirstName, LastName, lecturerID);
========
        super(FirstName, LastName, lecturerID,password);
>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
        this.selectStudent = null;
    }

    // Find Students that send their requests to the Advisor
    public void findAwaitingStudents() {
        int numberOfStudents = students.size();
        for (int index = 0 ; index < numberOfStudents ; index++) {
            Student student = students.get(index);
<<<<<<<< HEAD:iter1/src/Advisor.java
            if (student.getRequest().equals("true")) {
                awaitingStudents.add(student);
            }      
========
            if (!awaitingStudents.contains(student) && student.getRequest().equals("true")) {
                awaitingStudents.add(student);
            }           
>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
        }
    }

    // Find the correspondence index of Student Object
    public void selectStudent(int index) {
        index = index - 1;
        Student currentStudent = getAwaitingStudents().get(index);
        this.setSelectStudent(currentStudent);
    }

<<<<<<<< HEAD:iter1/src/Advisor.java
// Approve request of selected Student
    public void Approve() {
========
    // Approve request of selected Student
    public void approve() {
>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
        ArrayList<Course> selectCourses = this.selectStudent.getSelectedCourses();
        int numberOfCourses = selectCourses.size();
        for(int index = 0 ; index < numberOfCourses ; index++) {
            Course course = selectCourses.get(index);
            this.selectStudent.addApprovedCourse(course);
            course.enrollStudent(this.selectStudent);
        }
        this.selectStudent.dropAllSelectedCourses();
        this.selectStudent.setRequest("null");
        this.removeAwaitingStudent(this.selectStudent);
        this.setSelectStudent(null);
    }

    // Dissapprove Student request
<<<<<<<< HEAD:iter1/src/Advisor.java
    public void Disapprove() {
========
    public void disapprove() {
>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
        ArrayList<Course> selectCourses = this.selectStudent.getSelectedCourses();
        int numberOfCourses = selectCourses.size();
        for(int index = 0 ; index < numberOfCourses ; index++) {
            Course course = selectCourses.get(index);
<<<<<<<< HEAD:iter1/src/Advisor.java
            this.selectStudent.addSelectableCourse(course);
            course.setQuota(course.getQuota() + 1);
========
            course.setQuota(course.getQuota() + 1);
            this.selectStudent.getSyllabus().removeCourseFromSyllabus(course);
>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
        }
        this.selectStudent.dropAllSelectedCourses();
        this.selectStudent.setRequest("false");
        this.removeAwaitingStudent(this.selectStudent);
        this.setSelectStudent(null);
    }

    // Send Informative Message to the Student
    public void sendNotification(String message, String type) {
        
        String defaultMessage = "";
        if (message.isEmpty()) {
            if(type.equals("A")) {
                defaultMessage = "The request is approved!";
            }
            else {
                defaultMessage = "The request is disapproved!";
            }
        }
        else {
            defaultMessage = message;
        }
        
        this.selectStudent.addUnreadNotification(defaultMessage);
    }

    public ArrayList<Student> getStudentList() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> student) {
        this.students = student;
    }

    public ArrayList<Student> getAwaitingStudents() {
        return this.awaitingStudents;
    }

    public void setAwaitingStudents(ArrayList<Student> awaitingstudent) {
        this.awaitingStudents = awaitingstudent;
    }
    
    // After approve or disapprove, update the awaitingStudent 
<<<<<<<< HEAD:iter1/src/Advisor.java
    public void removeAwaitingStudent(Student student) {
========
    private void removeAwaitingStudent(Student student) {
>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
    	int ind = awaitingStudents.indexOf(student);
        this.awaitingStudents.remove(ind);
    }

    public Student getSelectStudent() {
        return this.selectStudent;
    }

    public void setSelectStudent(Student student) {
        this.selectStudent = student;
    }
    
<<<<<<<< HEAD:iter1/src/Advisor.java
    public Password getPassword() {
        return password;
    }
========

>>>>>>>> ITERATION2-V2.0:src/PersonObject/Advisor.java
}