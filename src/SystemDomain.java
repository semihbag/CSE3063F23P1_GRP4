import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
            lecturers.add(new Lecturer(name,surname,new Id(lecturerId)));
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
            int year = courseJSON.getJSONObject(i).getInt("year");
            int quota = courseJSON.getJSONObject(i).getInt("quota");
            String day_hour = courseJSON.getJSONObject(i).getString("day_hour");
            String[] prerequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prerequisite"));

            Lecturer courseLecturer=null;
            for (Lecturer lecturer : lecturers) {
                if (lecturer.getLecturerId().getId().equals(courseJSON.getJSONObject(i).getString("lecturer"))) {
                    courseLecturer=lecturer;
                    break;
                }
            }

            Course course = null;
            if(courseJSON.getJSONObject(i).getBoolean("isSession")){
                String sessionId = courseJSON.getJSONObject(i).getString("sessionId");
                course = new CourseSession(new Id(courseId),name, quota, year ,day_hour,courseLecturer,new Id(sessionId));
            }
            else {
                course = new Course(new Id(courseId),name, quota, year, day_hour, courseLecturer);
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

            String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\" + allStudentFilesInput.nextLine())));
            JSONObject jsonStudent = new JSONObject(content);
            JSONObject transcript = jsonStudent.getJSONObject("transcript");
            JSONObject registration = jsonStudent.getJSONObject("registration");

            String id = jsonStudent.getString("id");
            String name = jsonStudent.getString("name");
            String lastname = jsonStudent.getString("lastname");
            String advisorID = jsonStudent.getString("advisor");
            String booleanString = jsonStudent.getString("request");
            String notification = jsonStudent.getString("notification");
            String password = jsonStudent.getString("password");

            String[] failedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("failedcourses"));
            String[] completedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("completedcourses"));
            int year = transcript.getInt("year");
            double gpa = transcript.getDouble("gpa");

            String[] selectedCoursesAr = jsonArrToStrArr(registration.getJSONArray("selectedcourses"));
            String[] approvedCoursesAr = jsonArrToStrArr(registration.getJSONArray("approvedcourses"));

            ArrayList<Course> failedCourses = setStudentCourses(failedCoursesAr);
            ArrayList<Course> completedCourses = setStudentCourses(completedCoursesAr);
            ArrayList<Course> selectedCourses = setStudentCourses(selectedCoursesAr);
            ArrayList<Course> approvedCourses = setStudentCourses(approvedCoursesAr);

            Student crtStudent = new Student(name, lastname, new Id(id), new Password(password), findAdvisor(advisorID),
                    new Transcript(gpa, year, completedCourses, failedCourses), courses);

            crtStudent.setRequest(booleanString);
            crtStudent.setNotification(notification);
            crtStudent.setSelectedCourses(selectedCourses);
            crtStudent.setApprovedCourses(approvedCourses);
            crtStudent.filterCourses();
            students.add(crtStudent);
        }
        assignStudentsToAdvisor();
        fillStudentListCourse();
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
            MainMenuPageStudent mainStudent = new MainMenuPageStudent(pageContentCreator.crateMainMenuPageStudentContent());
			pages.add(mainStudent);

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

			MyStudentsPage myStudents = new MyStudentsPage(pageContentCreator.createMyStudentsPageContent(advisor.getStudentList()));
			pages.add(myStudents);

			EvaluateRequestsPage evaluateRequest = new EvaluateRequestsPage(pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequest.setNumberOfRequest(advisor.getAwaitingStudents().size());
			pages.add(evaluateRequest);

			SelectedStudentRequestPage selectedStudentRequest = new SelectedStudentRequestPage(pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));
			pages.add(selectedStudentRequest);
		}
		return pages;
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