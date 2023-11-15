import java.util.ArrayList;

public class Student extends Person {
    private ID studentId;
    private Password password;
    private int year;
    private Advisor advisor;
    private Transcript transcript;
    private ArrayList<Course> selectableCourses;
    private ArrayList<Course> selectedCourses;
    private ArrayList<Course> approvedCourses;
    private Boolean request;
    private String notification;

    public Student(String firstName, String lastName, ID studentID, Password password, int year, Advisor advisor, Transcript transcript, ArrayList<Course> selectableCourses, ArrayList<Course> selectedCourses, ArrayList<Course> approvedCourses, boolean request, String notification, ArrayList <Course> cirriculum) {
        super(firstName, lastName);
        this.studentId = studentID;
        this.password = password;
        this.year = year;
        this.advisor = advisor;
        this.transcript = transcript;
        this.selectableCourses = selectableCourses;
        this.selectedCourses = selectedCourses;
        this.approvedCourses = approvedCourses;
        this.request = request;
        this.notification = notification;
    }

    public void addSelectableCourse(Course course){
        selectedCourses.add(course);
    }

    public void addApprovedCourse(Course course){
        approvedCourses.add(course);
    }

    public void dropAllSelectedCourses(){
        selectedCourses.clear();
    }
    

    // Getter - Setter Methods

    public ID getStudentId() {
        return studentId;
    }

    public void setStudentId(ID studentId) {
        this.studentId = studentId;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int semester) {
        this.year = semester;
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