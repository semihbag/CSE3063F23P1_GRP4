import java.util.ArrayList;

public class Student extends Person {
    private Id studentId;
    private Password password;

    private Advisor advisor;
    private Transcript transcript;
    private ArrayList<Course> selectableCourses = new ArrayList<Course>();
    private ArrayList<Course> selectedCourses = new ArrayList<Course>();;
    private ArrayList<Course> approvedCourses = new ArrayList<Course>();;
    private ArrayList<Course> curriculum= new ArrayList<Course>();;
    private Boolean request;
    private String notification;


    public Student(String firstName, String lastName, Id studentID, Password password, Advisor advisor, Transcript transcript, ArrayList <Course> curriculum) {
        super(firstName, lastName);
        this.studentId = studentID;
        this.password = password;
        this.advisor = advisor;
        this.transcript = transcript;
        this.curriculum = curriculum;
    }

    // Filters all courses in the curriculum according to the student's current semester and prerequisite course passing information
    public void filterCourses(ArrayList<Course> curriculum) {
        for (int i = 0; i < curriculum.size() ; i++){
            Course course = curriculum.get(i);
            if(!isSelectedCourse(course)){
                if(!isPassedCourse(course)){
                    if(isPrerequisiteCoursesPassed(course)) {
                        if(course.getYear() == transcript.getYear()){
                            if (isUnderQuota(course)) {
                                selectableCourses.add(course);
                            }
                        }
                        else if(course.getYear() == (transcript.getYear()+1)){
                            if (transcript.getGPA_100()>=75){
                                if (isUnderQuota(course)) {
                                    selectableCourses.add(course);
                                }
                            }
                        }
                        else if (course.getYear()<transcript.getYear()){
                            if (isFailedCourse(course)){
                                if(isUnderQuota(course)){
                                    selectableCourses.add(course);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public boolean isSelectedCourse(Course course){
        for(int i =0;  i < selectedCourses.size() ; i++) {
            if(selectedCourses.get(i).getCourseId().equals(course.getCourseId())){
                return true;
            }
        }
        return false;
    }

    public boolean isPassedCourse(Course course){
        for(int i= 0; i < transcript.getPassedCourses().size() ; i++){
            if (transcript.getPassedCourses().get(i).getCourseId().equals(course.getCourseId())){
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
            return true;
        }
        return false;
    }

    public boolean isFailedCourse(Course course) {
        for (int i = 0; i < transcript.getFailedCourses().size(); i++) {
            Course failedCourse = transcript.getFailedCourses().get(i);
            if (failedCourse.getCourseId().equals(course.getCourseId())) {
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
    	System.err.println("akemnflakfmp");
        request = Boolean.TRUE;
    }


    // Adds each selected course from the selectableCourses to the selectedCourses and deletes it from the selectableCourses
    public void addSelectedCourse(int i) {
    	if (this.getRequest() == false) {
		   Course course = selectableCourses.get(i-1);
	        if (selectedCourses.size() < 5){
	            selectedCourses.add(course);
	            course.setQuota(course.getQuota()-1);
	            removeAllSessions(course);
	        } else {
	            System.out.println("You exceed the course limits, you can just select five courses.");
	        }
    	}
    }

    // Deletes the course taken as a parameter from the selectedCourses
    public void dropCourse(int i) {
        Course course = selectedCourses.get(i-1);
        selectedCourses.remove(i-1);
        addAllSessions(course);
    }

    public void addAllSessions(Course course) {
        for (int i = 0; i < curriculum.size(); i++) {
            if (course.getCourseId().getId().equals(curriculum.get(i).getCourseId().getId())) {
                if (!selectableCourses.contains(curriculum.get(i))) {
                    selectableCourses.add(curriculum.get(i));
                }
            }
        }
    }

    public void removeAllSessions(Course course) {
        for (int i = 0; i < selectableCourses.size(); i++) {
            if (course.getCourseId().getId().equals(selectableCourses.get(i).getCourseId().getId())) {
                selectableCourses.remove(i);
                i--;
            }
        }
    }


    // Getter - Setter Methods

    public ArrayList<Course> getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(ArrayList<Course> curriculum) {
        this.curriculum = curriculum;
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