import java.util.ArrayList;

public class Advisor extends Lecturer {
    
    private ArrayList<Student> students;
    private ArrayList<Student> awaitingStudents;
    private Password password;

    // Constructor
    public Advisor(String FirstName, String LastName, int lecturerID, ArrayList<Student> students, ArrayList<Student> awaitingStudents, String password) {

        super(FirstName, LastName, lecturerID);
        if (students == null) {
            this.students = new ArrayList<Student>();
        }
        else {
            this.students = students;
        }
        this.awaitingStudents = findAwaitingStudents();
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
    public int selectStudent(int index) {
        
        index = index - 1;
        int numberOfStudents = awaitingStudents.size();
        if (index >= numberOfStudents || index < 0) {
            return -1;
        }
        else {
            return index;
        }
    }

    // Approve request of selected Student
    public void Approve(Student student) {

        ArrayList<Course> selectCourses = student.getSelectedCourses();
        int numberOfCourses = selectCourses.size();
        for(int index = 0 ; index < numberOfCourses ; index++) {
            Course course = selectCourses.get(index);
            student.addApprovedCourses(course);
            student.dropSelectedCourses(course);
        }
        student.updateRequestNone();
        this.removeAwaitingStudent(student);
    }

    // Dissapprove Student request
    public void Disapprove(Student student) {

        ArrayList<Course> selectCourses = student.getSelectedCourses();
        int numberOfCourses = selectCourses.size();
        for(int index = 0 ; index < numberOfCourses ; index++) {
            Course course = selectCourses.get(index);
            student.updateSelectableCourses(course);
            student.dropSelectedCourses(course);
        }
        student.updateRequestFalse();
        this.removeAwaitingStudent(student);
    }

    // Send Informative Message to the Student
    public void sendNotification(String message, Student student, String type) {
        
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
        student.updateMessage(defaultMessage);
    }

    public ArrayList<Student> getStudentList() {
        return this.students;
    }

    public ArrayList<Student> getAwaitingStudents() {
        return this.awaitingStudents;
    }
    
    // After approve or disapprove, update the awaitingStudent 
    public void removeAwaitingStudent(Student student) {
        this.awaitingStudents.remove(student);
    }
    
    public String getPassword() {
        return password.getPassword();
    }
}