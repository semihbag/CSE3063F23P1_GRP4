import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Page.*;

public class SystemDomain {
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Advisor> advisors = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    //Create all objects and fill their attributes
    public SystemDomain() throws JSONException, IOException {
        createLecturers();
        createAdvisors();
        createCourses();
        createStudents();
    }

    //Create all lecturer objects
    public void createLecturers() throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\lecturers.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray lecturerJSON = jsonObject.getJSONArray("lecturers");
        for(int i =0 ; i< lecturerJSON.length();i++){
            String name = lecturerJSON.getJSONObject(i).getString("name");
            String surname = lecturerJSON.getJSONObject(i).getString("surname");
            String lecturerId = lecturerJSON.getJSONObject(i).getString("lecturerId");
            String password = lecturerJSON.getJSONObject(i).getString("password");
            lecturers.add(new Lecturer(name,surname,new Id(lecturerId),new Password(password)));
        }
    }

    //Create all advisor objects
    private void createAdvisors() throws JSONException, IOException{
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\advisors.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray advisorJSON = jsonObject.getJSONArray("advisors");
        for(int i =0 ; i< advisorJSON.length();i++){
            String name = advisorJSON.getJSONObject(i).getString("name");
            String surname = advisorJSON.getJSONObject(i).getString("surname");
            String advisorId = advisorJSON.getJSONObject(i).getString("advisorId");
            String password = advisorJSON.getJSONObject(i).getString("password");
            Advisor advisor = new Advisor(name,surname,new Id(advisorId),new Password(password));
            advisors.add(advisor);
            lecturers.add(advisor);
        }
    }

    //Create all courses
    private void createCourses() throws JSONException, IOException{
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");
        for(int i =0 ; i< courseJSON.length();i++){
            String courseId =courseJSON.getJSONObject(i).getString("id");
            String name = courseJSON.getJSONObject(i).getString("name");
            int term = courseJSON.getJSONObject(i).getInt("term");
            int quota = courseJSON.getJSONObject(i).getInt("quota");
            String[] prerequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prerequisite"));
            int credit = courseJSON.getJSONObject(i).getInt("credit");
            String courseTypeStr = courseJSON.getJSONObject(i).getString("type");
            Lecturer courseLecturer=null;
            for (Lecturer lecturer : lecturers) {
                if (lecturer.getLecturerId().getId().equals(courseJSON.getJSONObject(i).getString("lecturer"))) {
                    courseLecturer=lecturer;
                    break;
                }
            }
            ArrayList<CourseSchedule> courseSchedule = new ArrayList<CourseSchedule>();
            fillCourseSchedule(courseJSON.getJSONObject(i).getJSONArray("day"),courseJSON.getJSONObject(i).getJSONArray("hour"),courseSchedule);
            CourseType courseType = setCourseType(courseTypeStr);
            Course course = null;
            if(courseJSON.getJSONObject(i).getBoolean("isSession")){
                String sessionId = courseJSON.getJSONObject(i).getString("sessionId");
                course = new CourseSession(new Id(courseId),name, quota, term,courseLecturer,new Id(sessionId),courseSchedule,credit, courseType);
            }
            else {
                course = new Course(new Id(courseId), name, quota, term, courseLecturer, courseSchedule, credit, courseType);
            }

            for(String str: prerequisiteId){
                for(Course crs: courses){
                    if(crs.getCourseId().getId().equals(str)){
                        course.getPrerequisiteCourses().add(crs);
                        break;
                    }
                }
            }
            courses.add(course);
        }
        assignCoursesToLecturer();
    }

    //Create all student objects
    public void createStudents() throws JSONException, IOException {
        File allStudentFiles = new File("src\\JSON_Files\\student_json.txt");
        Scanner allStudentFilesInput = new Scanner(allStudentFiles);
        while (allStudentFilesInput.hasNextLine()) {

            String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\Students\\" + allStudentFilesInput.nextLine())));
            JSONObject jsonStudent = new JSONObject(content);
            JSONObject transcript = jsonStudent.getJSONObject("transcript");
            JSONObject registration = jsonStudent.getJSONObject("registration");

            String id = jsonStudent.getString("id");
            String name = jsonStudent.getString("name");
            String lastname = jsonStudent.getString("lastname");
            String advisorID = jsonStudent.getString("advisor");
            String booleanString = jsonStudent.getString("request");
            String[] readNotification = jsonArrToStrArr(jsonStudent.getJSONArray("readNotification"));
            String[] unreadNotification = jsonArrToStrArr(jsonStudent.getJSONArray("unreadNotification"));
            String password = jsonStudent.getString("password");

            String[] failedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("failedCourses"));
            String[] completedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("passedCourses"));
            String[] gradesPassed = jsonArrToStrArr(transcript.getJSONArray("gradesPassed"));
            String[] gradesFailed = jsonArrToStrArr(transcript.getJSONArray("gradesFailed"));
            int[] termPassed = jsonArrToIntArr(transcript.getJSONArray("termPassed"));

            String[] selectedCoursesAr = jsonArrToStrArr(registration.getJSONArray("selectedCourses"));
            String[] approvedCoursesAr = jsonArrToStrArr(registration.getJSONArray("approvedCourses"));

            ArrayList<GradeClass> failedCourses = setTranscriptCourses(failedCoursesAr, gradesFailed, termPassed);
            ArrayList<GradeClass> passedCourses = setTranscriptCourses(completedCoursesAr, gradesPassed, termPassed);

            ArrayList<Course> selectedCourses = setStudentCourses(selectedCoursesAr);
            ArrayList<Course> approvedCourses = setStudentCourses(approvedCoursesAr);

            int term = transcript.getInt("term");
            double gpa = calculateGPA(passedCourses, failedCourses);

            Student crtStudent = new Student(name, lastname, new Id(id), new Password(password), findAdvisor(advisorID),
                    new Transcript(gpa, term, passedCourses, failedCourses), courses);

            crtStudent.setRequest(booleanString);
            crtStudent.setReadNotifications(new ArrayList<>(Arrays.asList(readNotification)));
            crtStudent.setUnreadNotifications(new ArrayList<>(Arrays.asList(unreadNotification)));
            crtStudent.setSelectedCourses(selectedCourses);
            crtStudent.setApprovedCourses(approvedCourses);
            crtStudent.filterCourses();
            students.add(crtStudent);
        }
        assignStudentsToAdvisor();
        fillStudentListCourse();
    }

    private double calculateGPA(ArrayList<GradeClass> passedCourses, ArrayList<GradeClass> failedCourses) {
        double gpa=0;
        int totalCredit=0;
        for(GradeClass current: passedCourses){
            gpa += (current.getCourse().getCredit())*(letterToGrade(current.getGrade()));
            totalCredit+=current.getCourse().getCredit();
        }
        for(GradeClass current: failedCourses){
            gpa += (current.getCourse().getCredit())*(letterToGrade(current.getGrade()));
            totalCredit+=current.getCourse().getCredit();
        }
        if(totalCredit==0){
            return 0;
        }
        else{
            return gpa/totalCredit;
        }
    }

    private CourseType setCourseType(String courseTypeStr) {
        return switch (courseTypeStr) {
            case "mandatory" -> CourseType.MANDATORY;
            case "technical" -> CourseType.TECHNICAL;
            case "nontechnical" -> CourseType.NONTECHNICAL;
            case "faculty" -> CourseType.FACULTY;
            default -> null;
        };
    }

    private double letterToGrade(Grade grade){
        return switch (grade){
            case AA -> 4.0;
            case BA -> 3.5;
            case BB -> 3.0;
            case CB -> 2.5;
            case CC -> 2.0;
            case DC -> 1.5;
            case DD -> 1.0;
            case FD -> 0.5;
            case FF, DZ -> 0.0;
        };
    }

    private ArrayList<GradeClass> setTranscriptCourses(String[] transcriptCourses, String[] grades, int[] termPassed) {
        ArrayList<GradeClass> transcriptCourseList = new ArrayList<>();
        for (int i = 0; i < transcriptCourses.length; i++) {
            for (Course course : courses) {
                if (course.getCourseId().getId().equals(transcriptCourses[i])
                        && !courseExists(course, transcriptCourseList)) {
                    transcriptCourseList.add(new GradeClass(course, getCourseGrade(grades[i])));
                }
            }
        }
        return transcriptCourseList;
    }

    private boolean courseExists(Course course, ArrayList<GradeClass> transcriptCourseList) {
        for (GradeClass gradeClass : transcriptCourseList) {
            if (course.getCourseId().getId().equals(gradeClass.getCourse().getCourseId().getId())) {
                return true;
            }
        } return false;
    }


    private ArrayList<Course> setStudentCourses(String[] studentCoursesAr) {
        ArrayList<Course> studentCoursesList = new ArrayList<>();
        for (String s : studentCoursesAr) {
            for (int j = 0; j < getCourses().size(); j++) {
                if ((getCourses().get(j) instanceof CourseSession &&
                        (getCourses().get(j).getCourseId().getId() + "." + ((CourseSession) getCourses().get(j)).getSessionId().getId()).equals(s))
                        || s.equals(getCourses().get(j).getCourseId().getId())) {
                    studentCoursesList.add(getCourses().get(j));
                }
            }
        } return studentCoursesList;
    }

    //Fill the classroom information
    public void fillStudentListCourse() throws JSONException, IOException{
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");
        for(int i=0; i<courseJSON.length();i++){
            JSONObject currentCourse = courseJSON.getJSONObject(i);
            String[] courseStudentsId = jsonArrToStrArr(currentCourse.getJSONArray("studentList"));
            for (String currStudentId : courseStudentsId) {
                for (Student st : students) {
                    if (st.getStudentId().getId().equals(currStudentId) && (!(courses.get(i).getStudentList().contains(st)))) {
                        courses.get(i).getStudentList().add(st);
                        break;
                    }
                }
            }
        }
    }

    public String[] jsonArrToStrArr(JSONArray jsonArray) throws JSONException {
        String[] strings = new String[jsonArray.length()];
        for(int i=0; i<jsonArray.length();i++){
            strings[i]=jsonArray.getString(i);
        }
        return strings;
    }

    private int[] jsonArrToIntArr(JSONArray jsonArray) throws JSONException {
        int[] ints = new int[jsonArray.length()];
        for(int i=0; i<jsonArray.length();i++){
            ints[i]=jsonArray.getInt(i);
        }
        return ints;
    }

    private void assignStudentsToAdvisor() {
        for (Student student : students) {
            for (Advisor advisor : advisors) {
                if (student.getAdvisor().getLecturerId().getId().equals(advisor.getLecturerId().getId())) {
                    advisor.getStudentList().add(student);
                    break;
                }
            }
        }
    }

    private void assignCoursesToLecturer() {
        for (Course course : courses) {
            for (Lecturer lecturer : lecturers) {
                if (course.getLecturer().getLecturerId().getId().equals(lecturer.getLecturerId().getId())) {
                    lecturer.getGivenCourses().add(course);
                }
            }
        }
    }

    private Advisor findAdvisor(String advisorID) {
        Advisor advisor = null;
        for (int i = 0; i < getAdvisors().size(); i++) {
            if (getAdvisors().get(i).getLecturerId().getId().equals(advisorID)) {
                advisor = getAdvisors().get(i);
            }
        }
        return advisor;
    }

    //PAGE MERGE WITH SYSTEM DOMAIN
	public ArrayList<Page> createPages(Person currentUser){
		PageContentCreator pageContentCreator = new PageContentCreator();
		ArrayList<Page> pages = new ArrayList<>();
		if (currentUser instanceof Student student) {
            MainMenuPageStudent mainStudent = new MainMenuPageStudent(pageContentCreator.createMainMenuPageStudentContent());
			pages.add(mainStudent);

			ProfilePageStudent profile = new ProfilePageStudent(pageContentCreator.createProfilePageStudentContent(student));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(pageContentCreator.createChangePasswordPage());
			pages.add(cPassword);
			
			MyNotificationsPage notifications = new MyNotificationsPage(pageContentCreator.createMyNotificationsPageContent(student.getUnreadNotifications(), student.getReadNotifications()));
			pages.add(notifications);
			
			TranscriptPage transcript = new TranscriptPage(pageContentCreator.createTranscriptPageContent(student));
			pages.add(transcript);
			
			SyllabusPageStudent syllabus = new SyllabusPageStudent(pageContentCreator.createSyllabusPageContent(student.getSyllabus()));
			pages.add(syllabus);

			AllCoursesPage allCourses = new AllCoursesPage(pageContentCreator.createAllCoursesPageContent(student.getCurriculum()));
			pages.add(allCourses);

			SelectableCoursesPage selectable = new SelectableCoursesPage(pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectable.setNumberOfSelectableCourses(student.getSelectableCourses().size());
			pages.add(selectable);

			SelectedCoursesPage selected = new SelectedCoursesPage(pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
			selected.setNumberOfDropableCourses(student.getSelectedCourses().size());
			pages.add(selected);

			ApprovedCoursesPage approved = new ApprovedCoursesPage(pageContentCreator.createApprovedCoursesPageContent(student.getApprovedCourses()));
			pages.add(approved);
		}
		else if (currentUser instanceof Advisor advisor) {
            MainMenuPageAdvisor mainAdvisor = new MainMenuPageAdvisor(pageContentCreator.createMainMenuPageAdvisorContent());
			pages.add(mainAdvisor);

			ProfilePageAdvisor profile = new ProfilePageAdvisor(pageContentCreator.createProfilePageAdvisorContent(advisor));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(pageContentCreator.createChangePasswordPage());
			pages.add(cPassword);
			
			MyStudentsPage myStudents = new MyStudentsPage(pageContentCreator.createMyStudentsPageContent(advisor.getStudentList()));
			pages.add(myStudents);

			EvaluateRequestsPage evaluateRequest = new EvaluateRequestsPage(pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequest.setNumberOfRequest(advisor.getAwaitingStudents().size());
			pages.add(evaluateRequest);

			SelectedStudentRequestPage selectedStudentRequest = new SelectedStudentRequestPage(pageContentCreator.createSelectedStudentsRequestPageContent(advisor.getSelectStudent()));
			pages.add(selectedStudentRequest);
		}
		else if (currentUser instanceof Lecturer lecturer) {
			MainMenuPageLecturer mainLecturer = new MainMenuPageLecturer(pageContentCreator.createMainMenuPageLecturerContent());
			pages.add(mainLecturer);
			
			ProfilePageLecturer profile = new ProfilePageLecturer(pageContentCreator.createProfilePageLecturerContent(lecturer));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(pageContentCreator.createChangePasswordPage());
			pages.add(cPassword);
			
			MyCoursesPage myCourses = new MyCoursesPage(pageContentCreator.createMyCoursesPageContent(lecturer));
			myCourses.setNumberOfCourses(lecturer.getGivenCourses().size());
			pages.add(myCourses);
			
			SelectedMyCoursePage selectedCourse = new SelectedMyCoursePage(pageContentCreator.createSelectedMyCoursePage(lecturer.getSelectedCourse()));
			pages.add(selectedCourse);
		}
		return pages;
	}

    public void fillCourseSchedule(JSONArray dayJsonArr,JSONArray hourJsonArr,ArrayList<CourseSchedule> courseSchedules) throws JSONException {
        String[] dayStrArr = jsonArrToStrArr(dayJsonArr);
        for(int i=0;i<dayJsonArr.length();i++){
            Day currentDay = getCourseDay(dayStrArr[i]);
            ArrayList<Hour> hours = new ArrayList<>();
            JSONArray currentHourJsonArr= hourJsonArr.getJSONArray(i);
            for(int j=0;j<currentHourJsonArr.length();j++){
                hours.add(getCourseHour(currentHourJsonArr.getString(j)));
            }
            courseSchedules.add(new CourseSchedule(currentDay,hours));
        }

    }
    public Grade getCourseGrade(String strGrade){
        return switch (strGrade.toUpperCase()) {
            case "AA" -> Grade.AA;
            case "BA" -> Grade.BA;
            case "BB" -> Grade.BB;
            case "CB" -> Grade.CB;
            case "CC" -> Grade.CC;
            case "DC" -> Grade.DC;
            case "DD" -> Grade.DD;
            case "FD" -> Grade.FD;
            case "FF" -> Grade.FF;
            case "DZ" -> Grade.DZ;
            default -> null;
        };
    }
    public Day getCourseDay(String strDay){
        return switch (strDay.toUpperCase()) {
            case "MONDAY" -> Day.MONDAY;
            case "TUESDAY" -> Day.TUESDAY;
            case "WEDNESDAY" -> Day.WEDNESDAY;
            case "THURSDAY" -> Day.THURSDAY;
            case "FRIDAY" -> Day.FRIDAY;
            default -> null;
        };
    }

    public Hour getCourseHour(String strHour){
        return switch (strHour.toUpperCase()) {
            case "8.30" -> Hour.H_08_30_09_20;
            case "9.30" -> Hour.H_09_30_10_20;
            case "10.30" -> Hour.H_10_30_11_20;
            case "11.30" -> Hour.H_11_30_12_20;
            case "13.00" -> Hour.H_13_00_13_50;
            case "14.00" -> Hour.H_14_00_14_50;
            case "15.00" -> Hour.H_15_00_15_50;
            case "16.00" -> Hour.H_16_00_16_50;
            case "17.00" -> Hour.H_17_00_17_50;
            case "18.00" -> Hour.H_18_00_18_50;
            case "19.00" -> Hour.H_19_00_19_50;
            case "20.00" -> Hour.H_20_00_20_50;
            case "21.00" -> Hour.H_21_00_21_50;
            default -> null;
        };
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }
}