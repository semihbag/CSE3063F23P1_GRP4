import java.util.ArrayList;

public class Student extends Person {
    private Id studentId;
    private Advisor advisor;
    private Transcript transcript;
    private Syllabus syllabus; //
    private int selectedCourseCredit; //
    private ArrayList<String> unreadNotifications; //
    private ArrayList<String> readNotifications; //
    private ArrayList<Course> selectableCourses = new ArrayList<Course>(), selectedCourses = new ArrayList<Course>(),
            approvedCourses = new ArrayList<Course>(), curriculum = new ArrayList<Course>();
    private String request;

    public Student(String firstName, String lastName, Id studentID, Password password, Advisor advisor,
                   Transcript transcript, Syllabus syllabus, ArrayList<Course> curriculum, ArrayList<String> readNotifications,
                   ArrayList<String> unreadNotification) {
        super(firstName, lastName,password);
        this.studentId = studentID;
        this.advisor = advisor;
        this.transcript = transcript;
        this.syllabus = syllabus; // Sorulacak
        this.selectedCourseCredit = 0; //
        this.unreadNotifications = unreadNotifications; //
        this.readNotifications = readNotifications; //
        this.curriculum = curriculum;
    }

    // Filters all courses in the curriculum according to the student's current
    // semester and prerequisite course passing information
    public void filterCourses() {
        for (int i = 0; i < curriculum.size(); i++) {
            Course course = curriculum.get(i);
            if (!isSelectedCourse(course) && !isPassedCourse(course) && isPrerequisiteCoursesPassed(course)
                    && isUnderQuota(course)
                    && (course.getYear() == transcript.getYear() || ((course.getYear() == (transcript.getYear() + 1)) &&
                    (transcript.getGPA_100() >= 75)) || isFailedCourse(course)) && !exceedNTE()) { //
                selectableCourses.add(course);
            }
        }
    }

    public boolean exceedNTE(){ //
        int ct = 0;
        for(int i= 0; i < transcript.getPassedCourses().size(); i++) {
            if(transcript.getPassedCourses().get(i).getCourseType() == CourseType.NONTECHNICAL){
                ct++;
            }
        }

        if(ct == 2){
            return true;
        }
        return false;
    }

    public boolean isSelectedCourse(Course course) {
        for (int i = 0; i < selectedCourses.size(); i++) {
            if (selectedCourses.get(i).getCourseId().getId().equals(course.getCourseId().getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean isPassedCourse(Course course) {
        for (int i = 0; i < transcript.getPassedCourses().size(); i++) {
            if (transcript.getPassedCourses().get(i).getCourseId().getId().equals(course.getCourseId().getId())) {
                return true;
            }
        }
        return false;
    }

    // Checks whether the student has passed the prerequisite courses to take the
    // course
    public boolean isPrerequisiteCoursesPassed(Course course) {
        for (int i = 0; i < course.getPrerequisiteCourses().size(); i++) {
            Course preCourse = course.getPrerequisiteCourses().get(i);
            if (!isPassedCourse(preCourse)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUnderQuota(Course course) {
        return 0 < course.getQuota();
    }

    public boolean isFailedCourse(Course course) {
        for (int i = 0; i < transcript.getFailedCourses().size(); i++) {
            Course failedCourse = transcript.getFailedCourses().get(i);
            if (failedCourse.getCourseId().getId().equals(course.getCourseId().getId())) {
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
    public void addSelectedCourse(int i) {
        Course course = selectableCourses.get(i - 1);

        if (this.getRequest().equals("false") && !syllabus.checkConflict(course)) {
            if (selectedCourseCredit + course.getCredit() < 40 && checkTermProject(course, transcript.getTotalCredit()) && checkCourseType(course.getCourseType())) { //
                selectedCourses.add(course);
                selectedCourseCredit += course.getCredit();
                course.setQuota(course.getQuota() - 1);
                removeAllSessions(course);
            } else {
                System.out.println("You exceed the course limits, you can just select five courses.");
            }
        }
    }

    public boolean checkCourseType(CourseType courseType){ //
        if(courseType == CourseType.NONTECHNICAL){
            checkNTE();
        }
        else if(courseType == CourseType.TECHNICAL){

        }
        return true;
    }

    public boolean checkNTE(){ //
        int ct = 0;

        //selected da iki tane
        for(int i = 0;  i < selectedCourses.size(); i++){
            if(selectedCourses.get(i).getCourseType() == CourseType.NONTECHNICAL) {
                ct++;
            }
        }

        if(ct == 0){
            return true;
        } else{
            System.out.println("You cannot select NTE course more than 1 at one term");
            return false;
        }
    }

    public boolean checkTermProject(Course course, int totalCredit){ //
        if(course.getCourseName().equals("Engineering Project 1")){
            if(totalCredit < 165){
                System.out.println("You don't have enough credit for Engineering Project 1\n");
                return false;
            }
        }
        else if(course.getCourseName().equals("Engineering Project 2")){
            if(totalCredit < 180){
                System.out.println("You don't have enough credit for Engineering Project 2\n");
                return false;
            }
        }
        return true;
    }

    void clearUnreadNotification(){ //
        readNotifications.addAll(unreadNotifications);
        unreadNotifications.clear();
    }

    void addUnreadNotification(String notification){ //
        unreadNotifications.add(notification);
    }

    // Deletes the course taken as a parameter from the selectedCourses
    public void dropCourse(int i) {
        Course course = selectedCourses.get(i - 1);
        selectedCourses.remove(i - 1);
        selectedCourseCredit -= course.getCredit();
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
    public Syllabus getSyllabus() {
        return syllabus;
    }
    //
    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
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

