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
    private ArrayList<Course> cirriculum;
    private Boolean request;
    private String notification;

    public Student(String firstName, String lastName, Id studentID, Password password, int year, Advisor advisor, Transcript transcript, ArrayList<Course> selectableCourses, ArrayList<Course> selectedCourses, ArrayList<Course> approvedCourses, Boolean request, String notification, ArrayList <Course> cirriculum) {
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
        this.cirriculum = cirriculum;

        filterCourses(cirriculum);
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

    public boolean isSelectedCourse(Course course){
        for(int i =0;  i < selectedCourses.size() ; i++) {
            if(selectedCourses.get(i).getCourseID() == course.getCourseID()){
                return true;
            }
        }
        return false;
    }

    public boolean isPassedCourse(Course course){
        for(int i= 0; i < transcript.getPassedCourses().size() ; i++){
            if (transcript.getPassedCourses().get(i).getCourseID() == course.getCourseID()){
                return true;
            }
        }
        return false;
    }

    // Checks whether the student has passed the prerequisite courses to take the course
    public boolean isPrerequisiteCoursesPassed(Course course){
        for(int i=0; i < course.getPrerequisiteCourses().size(); i++) {
            Course preCourse = course.getPrerequisiteCourses().get(i);
            if (!isPassedCourse(preCourse)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUnderQuota (Course course) {
        if(0 < course.getQuota()){
            return true; //yer var
        }
        return false;
    }

    public boolean isFailedCourse(Course course) {
        for (int i = 0; i < transcript.getFailedCourses().size(); i++) {
            Course failedCourse = transcript.getFailedCourses().get(i);
            if (failedCourse.getCourseID() == course.getCourseID()) {
                return true;
            }
        }
        return false;
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
    public void addSelectedCourse(int i) {
        Course course = selectableCourses.get(i-1);
        if (selectedCourses.size() < 5){
            selectedCourses.add(course);
            course.setQuota(course.getQuota()-1);
            removeAllSessions(course);
        } else {
            System.out.println("You exceed the course limits, you can just select five courses.");
        }
    }

    // Deletes the course taken as a parameter from the selectedCourses
    public void dropCourse(Course course) {
        int droppedCourseInd = selectedCourses.indexOf(course);
        selectedCourses.remove(droppedCourseInd);
        addAllSessions(course);
    }
    
    public void addAllSessions(Course course){
        for(int i  = 0; i< cirriculum.size(); i++){
            if (course.getCourseID() == cirriculum.get(i).getCourseID()){
                selectableCourses.add(course);
            }
        }
    }

    public void removeAllSessions(Course course){
        for(int i  = 0; i<selectableCourses.size(); i++){
            if (course.getCourseID() == selectableCourses.get(i).getCourseID()){
                selectableCourses.remove(course);
            }
        }
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