import java.util.ArrayList;

public class Advisor extends Lecturer {
    
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Student> awaitingStudents = new ArrayList<Student>();
    private Student selectStudent;
    private Password password;

    // Constructor
    public Advisor(String FirstName, String LastName, LecturerID lecturerID, Password password) {

        super(FirstName, LastName, lecturerID);
        this.selectStudent = null;
        this.password = password;
    }

    // Find Students that send their requests to the Advisor
    public ArrayList<Student> findAwaitingStudents() {

        ArrayList<Student> awaitingStudent = new ArrayList<Student>();
        int numberOfStudents = students.size();
        for (int index = 0 ; index < numberOfStudents ; index++) {
            Student student = students.get(index);
            if (student.getRequest() == true) {
                awaitingStudent.add(student);
            }      
        }
        return awaitingStudent;
    }

    // Find the correspondence index of Student Object
    public void selectStudent(int index) {
        
        index = index - 1;
        Student currentStudent = getAwaitingStudents().get(index);
        this.setSelectStudent(currentStudent);

    }

// Approve request of selected Student
    public void Approve() {
        ArrayList<Course> selectCourses = student.getSelectedCourses();
        int numberOfCourses = selectCourses.size();
        for(int index = 0 ; index < numberOfCourses ; index++) {
            Course course = selectCourses.get(index);
            this.selectStudent.addApprovedCourse(course);
            course.enrollStudent(this.selectStudent);
        }
        this.selectStudent.dropAllSelectedCourses();
        this.selectStudent.setRequest(null);
        this.setSelectStudent(null);
        this.removeAwaitingStudent(this.selectStudent);
    }

    // Dissapprove Student request
    public void Disapprove() {
        ArrayList<Course> selectCourses = student.getSelectedCourses();
        int numberOfCourses = selectCourses.size();
        for(int index = 0 ; index < numberOfCourses ; index++) {
            Course course = selectCourses.get(index);
            this.selectStudent.addSelectableCourse(course);
            course.setQuota(course.getQuota() + 1)
        }
        this.selectStudent.dropAllSelectedCourses();
        this.selectStudent.setRequest(false);
        this.setSelectStudent(null);
        this.removeAwaitingStudent(this.selectStudent);
    }

    // Send Informative Message to the Student
    public void sendNotification(String message, String type) {
        
        String defaultMessage = "";
        if (message.isEmpty()) {
            if(type == "A") {
                defaultMessage = "The request is approved!";
            }
            else {
                defaultMessage = "The request id disapproved!";
            }
        }
        else {
            defaultMessage = message;
        }
        this.selectStudent.setNotification(defaultMessage);
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
    public void removeAwaitingStudent(Student student) {
        this.awaitingStudents.remove(student);
    }

    public Student getSelectStudent() {
        return this.selectStudent;
    }

    public void setSelectStudent(Student student) {
        this.selectStudent = student;
    }
    
    public String getPassword() {
        return password.getPassword();
    }
}