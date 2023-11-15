import java.util.ArrayList;

public class Student extends Person {
    private Id studentId;
    private Password password;
    private int year;
    private Advisor advisor;
    private Transcript transcript;
    private ArrayList<Course> selectableCourses;
    private ArrayList<Course> selectedCourses;
    private ArrayList<Course> approvedCourses;
    private Boolean request;
    private String notification;

    public Student(String firstName, String lastName, Id studentID, Password password, int year, Advisor advisor, Transcript transcript, ArrayList<Course> selectedCourses, ArrayList<Course> approvedCourses, boolean request, String notification, ArrayList <Course> cirriculum) {
        super(firstName, lastName);
        this.studentId = studentID;
        this.password = password;
        this.year = year;
        this.advisor = advisor;
        this.transcript = transcript;
        this.selectedCourses = selectedCourses;
        this.approvedCourses = approvedCourses;
        this.request = request;
        this.notification = notification;

        filterCourses(cirriculum, transcript.getPassedCourses());

    }

    // Filters all courses in the curriculum according to the student's current semester and prerequisite course passing information
    public void filterCourses(ArrayList<Course> curriculum, ArrayList<Id> passedCourses) {
        for(int i = 0; i < curriculum.size() ; i++){
            Course currCourse = curriculum.get(i);
            if (currCourse.getYear() == year){
                for (int k = 0; k < passedCourses.size(); k++){
                    Id passedCourse = passedCourses.get(i);
                    if ( !passedCourse.equals(currCourse.getCourseID())){
                        if(isProvided(currCourse) && (currCourse.getStudentList().size() < currCourse.getQuota())){
                            selectableCourses.add(currCourse);
                        }
                    }
                }
            }
        }
    }

    // Checks whether the student has passed the prerequisite courses to take the course
    public boolean isProvided(Course course){
        for(int i=0; i < course.getPrerequisiteCourses().size(); i++) {
            Course preCourse = course.getPrerequisiteCourses().get(i);
            for (int k = 0; k < transcript.getPassedCourses().size(); k++){
                Id passCourse = transcript.getPassedCourses().get(k);
                if (!passCourse.equals(preCourse.getCourseID())){
                    return false;
                }
            }
        }
        return true;
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

    public void sendToApproval() {
        request = Boolean.TRUE;
    }


    // Adds each selected course from the selectableCourses to the selectedCourses and deletes it from the selectableCourses
    public void addSelectedCourse(Course course) {
        int courseInd = selectableCourses.indexOf(course);
        if (selectedCourses.size() < 5){
            selectedCourses.add(course);
            course.setQuota(course.getQuota()-1);
            selectableCourses.remove(courseInd);
        } else {
            System.out.println("You exceed the course limits, you can just select five courses.");
        }
    }

    // Deletes the course taken as a parameter from the selectedCourses
    public void dropCourse(Course course) {
        int droppedCourseInd = selectedCourses.indexOf(course);
        selectedCourses.remove(droppedCourseInd);
    }
    

    // Getter - Setter Methods

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