import java.util.ArrayList;

public class Student extends Person {
    private Id studentId;
    private Password password;
    private Advisor advisor;
    private Transcript transcript;

    private ArrayList<Course> selectableCourses = new ArrayList<>();
    private ArrayList<Course> selectedCourses = new ArrayList<>();
    private ArrayList<Course> approvedCourses = new ArrayList<>();
    private Boolean request;
    private String notification;

    public Student(String firstName, String lastName, Id studentId, Password password, Advisor advisor,
                   Transcript transcript) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.password = password;
        this.advisor = advisor;
        this.transcript = transcript;
    }

    public Id getStudentId() {
        return studentId;
    }

    public void setStudentId(Id studentId) {
        this.studentId = studentId;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }


    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public ArrayList<Course> getSelectableCourses() {
        return selectableCourses;
    }

    public void setSelectableCourses(ArrayList<Course> selectableCourses) {
        this.selectableCourses = selectableCourses;
    }

    public ArrayList<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(ArrayList<Course> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public ArrayList<Course> getApprovedCourses() {
        return approvedCourses;
    }

    public void setApprovedCourses(ArrayList<Course> approvedCourses) {
        this.approvedCourses = approvedCourses;
    }

    public Boolean getRequest() {
        return request;
    }

    public void setRequest(Boolean request) {
        this.request = request;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
