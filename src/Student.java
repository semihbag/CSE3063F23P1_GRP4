import java.util.ArrayList;

public class Student extends Person {
    private Id studentId;
    private Advisor advisor;
    private Transcript transcript;
    private int selectedCourseCredit = 0; //
    private ArrayList<String> unreadNotifications; //
    private ArrayList<String> readNotifications; //
    private ArrayList<Course> selectableCourses = new ArrayList<Course>(), selectedCourses = new ArrayList<Course>(),
            approvedCourses = new ArrayList<Course>(), curriculum = new ArrayList<Course>();
    private String request;

    public Student(String firstName, String lastName, Id studentID, Password password, Advisor advisor,
                   Transcript transcript, ArrayList<Course> curriculum) {
        super(firstName, lastName, password);
        this.studentId = studentID;
        this.advisor = advisor;
        this.transcript = transcript;
        this.curriculum = curriculum;

    }

    // Filters all courses in the curriculum according to the student's current
    // semester and prerequisite course passing information
    public void filterCourses() {
        if (approvedCourses.size() != 0){
            createSyllabus(approvedCourses);
        }else{
            createSyllabus(selectedCourses);
        }

        for (int i = 0; i < curriculum.size(); i++) {
            Course course = curriculum.get(i);
            if (!isSelectedCourse(course) && !isPassedCourse(course) && isPrerequisiteCoursesPassed(course) && isUnderQuota(course)
                    && (checkCourseType(course)  || isFailedCourse(course))){ //
                selectableCourses.add(course);
            }
        }
    }

    private boolean isSelectedCourse(Course course) {
        for (int i = 0; i < selectedCourses.size(); i++) {
            if (selectedCourses.get(i).getCourseId().getId().equals(course.getCourseId().getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPassedCourse(Course course) {
        for (int i = 0; i < transcript.getPassedCourses().size(); i++) {
            if (transcript.getPassedCourses().get(i).getCourse().getCourseId().getId().equals(course.getCourseId().getId())) {
                return true;
            }
        }
        return false;
    }

    // Checks whether the student has passed the prerequisite courses to take the
    // course
    private boolean isPrerequisiteCoursesPassed(Course course) {
        for (int i = 0; i < course.getPrerequisiteCourses().size(); i++) {
            Course preCourse = course.getPrerequisiteCourses().get(i);
            if (!isPassedCourse(preCourse)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUnderQuota(Course course) {
        return 0 < course.getQuota();
    }

    private boolean isFailedCourse(Course course) {
        for (int i = 0; i < transcript.getFailedCourses().size(); i++) {
            GradeClass failedCourse = transcript.getFailedCourses().get(i);
            if (failedCourse.getCourse().getCourseId().getId().equals(course.getCourseId().getId()) &&
                    transcript.getTerm()%2 == course.getTerm()%2) { // tek çift dönem olayı eklendi
                return true;
            }
        }
        return false;
    }

    public void addSelectableCourse(Course course) {
        selectableCourses.add(course);
    }

    public void addApprovedCourse(Course course) {
        approvedCourses.add(course);
    }

    public void dropAllSelectedCourses() {
        selectedCourses.clear();
        selectedCourseCredit = 0;
    }

    public void sendToApproval() {
        request = "true";
    }

    // Adds each selected course from the selectableCourses to the selectedCourses
    // and deletes it from the selectableCourses
    public boolean addSelectedCourse(int i) {
        Course course = selectableCourses.get(i - 1);
        if (this.getRequest().equals("false")) {
            if (selectedCourseCredit + course.getCredit() < 40 ) {//
                //NTE NT FACULTY

                CourseType courseType = course.getCourseType();
                if(courseType != CourseType.MANDATORY){
                    if(exceedTerm(courseType)){
                        return false;
                    }
                }
                if(!getSyllabus().checkConflict(course)) {
                    selectedCourses.add(course);
                    selectedCourseCredit += course.getCredit();
                    course.setQuota(course.getQuota() - 1);
                    removeAllSessions(course);
                    getSyllabus().addCourseToSyllabus(course);
                    return true;
                }
                else {
                    return false;
                }

            }
            System.out.println("You exceed selectable course credit limit!!");

        }
        return false;
    }


    private boolean checkCourseType(Course course){ // dönemini ve üsten alma olayı içeriyor (mandatoryde hem üst hem kendi dönemi olayı var))
        CourseType courseType = course.getCourseType();
        if(courseType == CourseType.NONTECHNICAL && transcript.getTerm() >= 2){
            if(!exceed(courseType, 2) ) { //eğitim hayatı boyunca max 2, her dönemde max 1 alır
                return true;
            }
        }
        else if(courseType == CourseType.TECHNICAL){
            if((transcript.getTerm() == 7 || transcript.getTerm() == 8)){
                return true;
            }
        }
        else if(courseType == CourseType.FACULTY){
            if((transcript.getTerm() == 7  || transcript.getTerm() == 8) && !exceed(courseType, 1)){
                return true;
            }
        }  else if(courseType == CourseType.MANDATORY){
            if(course.getCourseName().equals("Engineering Project I") ||
                    course.getCourseName().equals("Engineering Project II")){
                if(transcript.getTotalCredit() >= 165){
                    return true;
                }
                return false;
            }
            else if(course.getTerm() == transcript.getTerm()) {
                return true;
            }
            else if((transcript.getGPA_100() >= 3.0 && transcript.getTerm() >= 3) && ((course.getCourseName().equals("Is Sagligi ve Guvenligi I") || course.getCourseName().equals("Is Sagligi ve Guvenligi II")) ||
                    (course.getTerm() == transcript.getTerm() +2))) {
                        return true;
                    }
        }
        return false;
    }


    private boolean exceed(CourseType type, int limit){ //Bugüne kadar bu tipdeki dersten kaç tane aldığını söylüyor
        int ct = 0;
        for(int i= 0; i < transcript.getPassedCourses().size(); i++) {
            if(transcript.getPassedCourses().get(i).getCourse().getCourseType() == type){
                ct++;
            }
        }

        if(ct == limit){
            return true;
        }
        return false;
    }

    private boolean exceedTerm(CourseType courseType){ //bu dönemdeki seçtiklerini kontrol ediyor
        int ct = 0;

        //selected da iki tane
        for(int i = 0;  i < selectedCourses.size(); i++){
            if(selectedCourses.get(i).getCourseType() == courseType) {
                ct++;
            }
        }

        if(ct == 0){
            return false;
        } else{
            System.out.println("You cannot select " + courseType +  " course more than 1 at one term");
            return true;
        }
    }

    public boolean checkTermProject(Course course, int totalCredit){ //
        if(course.getCourseName().equals("Engineering Project I") ||
                course.getCourseName().equals("Engineering Project II")){
            System.out.println("Engineering Project 1 e girdi");
            if(totalCredit < 165){
                return true;
            }
        }
        return false;
    }

    public void clearUnreadNotification(){ //
        readNotifications.addAll(unreadNotifications);
        unreadNotifications.clear();
    }

    public void addUnreadNotification(String notification){ //
        unreadNotifications.add(notification);
    }

    // Deletes the course taken as a parameter from the selectedCourses
    public void dropCourse(int i) {
        Course course = selectedCourses.get(i - 1);
        selectedCourses.remove(i - 1);
        selectedCourseCredit -= course.getCredit();
        addAllSessions(course);
    }

    private void addAllSessions(Course course) {
        for (int i = 0; i < curriculum.size(); i++) {
            if (course.getCourseId().getId().equals(curriculum.get(i).getCourseId().getId())) {
                if (!selectableCourses.contains(curriculum.get(i))) {
                    selectableCourses.add(curriculum.get(i));
                }
            }
        }
    }

    private void removeAllSessions(Course course) {
        for (int i = 0; i < selectableCourses.size(); i++) {
            if (course.getCourseId().getId().equals(selectableCourses.get(i).getCourseId().getId())) {
                selectableCourses.remove(i);
                i--;
            }
        }
    }

    @Override
    public void createSyllabus(ArrayList<Course> courses) {
        getSyllabus().fillSyllabus(courses);
    }

    // Getter - Setter Methods
    public ArrayList<Course> getCurriculum() {
        return curriculum;
    }

    public Id getStudentId() {
        return studentId;
    }


    public Advisor getAdvisor() {
        return advisor;
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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    //
    public int getSelectedCourseCredit() {
        return selectedCourseCredit;
    }

    //
    public void setSelectedCourseCredit(int selectedCourseCredit) {
        this.selectedCourseCredit = selectedCourseCredit;
    }

    //
    public ArrayList<String> getUnreadNotifications() {
        return unreadNotifications;
    }
    //
    public void setUnreadNotifications(ArrayList<String> unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }

    //
    public ArrayList<String> getReadNotifications() {
        return readNotifications;
    }
    //
    public void setReadNotifications(ArrayList<String> readNotifications) {
        this.readNotifications = readNotifications;
    }

}


