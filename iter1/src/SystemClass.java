// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import systemMessagePackage.*;
import userInfoPackage.*;
import userInterfacePackage.*;
import pagePackage.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class SystemClass {
    private SystemDomain domain;
    private Person currentUser;
    private PageContentCreator pageContentCreator;
    private UserInterface userInterface;

    //Constructor
    public SystemClass(UserInterface u) throws JSONException, IOException {
        pageContentCreator = new PageContentCreator();
    	userInterface = u;
		LoginPage login = new LoginPage("welcome");
		userInterface.addPage(login);
		userInterface.setCurrentPage(PageType.LOGIN_PAGE);
    	domain = new SystemDomain();
    }

    //Start running the code
	public void run() {
		while (true) {
			userInterface.display();
			listenUserInterface(userInterface.getSystemMessage());
		}
	}

    //Login with the given user info
    public void login(UserInfo userInfo) {
        boolean userFound = false;
        if (userInfo.getUsername().charAt(0) == 'o') {
            ArrayList<Student> students = domain.getStudents();
            for (Student student : students) {
                if (("o" + student.getStudentId().getId()).equals(userInfo.getUsername()) &&
                        student.getPassword().getPassword().equals(userInfo.getPassword())) {
                    setCurrentUser(student);
                    userFound = true;
                    userInterface.setPages(domain.createPages(currentUser));
                    userInterface.setCurrentPage(PageType.MAIN_MENU_PAGE_STUDENT);
                    break;
                }
            }
        } else if (userInfo.getUsername().charAt(0) == 'a') {
            ArrayList<Advisor> advisors = domain.getAdvisors();
            for (Advisor advisor : advisors) {
                if (("a" + advisor.getLecturerId().getId()).equals(userInfo.getUsername()) &&
                        advisor.getPassword().getPassword().equals(userInfo.getPassword())) {
                    advisor.findAwaitingStudents();
                    System.err.println(advisor.getAwaitingStudents().size());
                	setCurrentUser(advisor);
                    userFound = true;
                    userInterface.setPages(domain.createPages(currentUser));
                    userInterface.setCurrentPage(PageType.MAIN_MENU_PAGE_ADVISOR);
                    break;
                }
            }
        } if (!userFound) {
            System.out.println("Username/Password incorrect.");
        }
    }

    //Logout from an account
    public void logout() {
    	userInterface.resetPages();
    	LoginPage login = new LoginPage("welcome");
		userInterface.addPage(login);
		userInterface.setCurrentPage(PageType.LOGIN_PAGE);
        setCurrentUser(null);
    }

    //Exit the system
    public void exit() throws JSONException, IOException {
        updateStudentJSON();
        updateCourseStudentData();
        updateCourseQuotaData();
        System.exit(0);
    }

    //Update course quota after selections in JSON files
    public void updateCourseQuotaData() throws IOException, JSONException {
        String content = null;
        content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");

        int j=0;
        for(int i=0; i<courseJSON.length();i++){
            JSONObject curr = courseJSON.getJSONObject(i);
            if(curr.getBoolean("hasSession")){
                JSONArray currCourseJSON = courseJSON.getJSONObject(i).getJSONArray("session");
                int k=0;
                for(k=0;k<currCourseJSON.length();k++){
                    JSONObject currSession = currCourseJSON.getJSONObject(k);
                    currSession.put("quota",domain.getCourses().get(j).getQuota());
                }
                j=j+k;
            }
            else{
                curr.put("quota",domain.getCourses().get(j).getQuota());
                j++;
            }
        }
        Files.write(Paths.get("src\\JSON_Files\\courses.json"),jsonObject.toString(4).getBytes(),StandardOpenOption.TRUNCATE_EXISTING);

    }

    //Update courses student lists after selection in JSON files
    public void updateCourseStudentData() throws IOException, JSONException {
        String content = null;
        content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");

        int j=0;
        for(int i=0; i<courseJSON.length();i++){
            JSONObject curr = courseJSON.getJSONObject(i);
            if(curr.getBoolean("hasSession")){
                JSONArray currCourseJSON = courseJSON.getJSONObject(i).getJSONArray("session");
                int k=0;
                for(k=0;k<currCourseJSON.length();k++){
                    JSONObject currSession = currCourseJSON.getJSONObject(k);
                    currSession.put("studentList",studentToJsonArray(domain.getCourses().get(j).getStudentList()));
                }
                j=j+k;
            }
            else{
                curr.put("studentList",studentToJsonArray(domain.getCourses().get(i).getStudentList()));
                j++;
            }
        }
        Files.write(Paths.get("src\\JSON_Files\\courses.json"),jsonObject.toString(4).getBytes(),StandardOpenOption.TRUNCATE_EXISTING);
    }

    //Update student infos in JSON files
    public void updateStudentJSON() throws JSONException, IOException {
        for (int i = 0; i < domain.getStudents().size(); i++) {
            String studentId = domain.getStudents().get(i).getStudentId().getId();
            Path path = Path.of("src\\JSON_Files\\" + studentId + ".json");
            String content = new String(Files.readAllBytes(path));
            JSONObject jsonStudent = new JSONObject(content);
            JSONObject registration = jsonStudent.getJSONObject("registration");

            ArrayList<Course> selected = domain.getStudents().get(i).getSelectedCourses();
            ArrayList<Course> approved = domain.getStudents().get(i).getApprovedCourses();

            registration.put("selectedcourses",transcriptCourses(selected));
            registration.put("approvedcourses", transcriptCourses(approved));

            jsonStudent.put("request", domain.getStudents().get(i).getRequest());
            jsonStudent.put("notification", domain.getStudents().get(i).getNotification());
            Files.write(path, jsonStudent.toString(4).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    //Create an array for JSON files update
    private String[] studentToJsonArray(ArrayList<Student> students){
        String[] studentIds = new String[students.size()];
        for(int i=0; i<students.size();i++){
            studentIds[i] = students.get(i).getStudentId().getId();
        }
        return  studentIds;
    }

    //Create an array for JSON files update
    private String[] transcriptCourses(ArrayList<Course> transcriptCoursesList) {
        String[] transcriptCoursesAr = new String[transcriptCoursesList.size()];
        for (int i = 0; i < transcriptCoursesList.size(); i++) {
            if (transcriptCoursesList.get(i) instanceof CourseSession) {
                CourseSession crsSession = (CourseSession) transcriptCoursesList.get(i);
                transcriptCoursesAr[i] = crsSession.getCourseId().getId() + "." + crsSession.getSessionId().getId();
            } else {
                transcriptCoursesAr[i] = transcriptCoursesList.get(i).getCourseId().getId();
            }
        } return transcriptCoursesAr;
    }

    //PAGE MERGE WITH SYSTEM PROCESSES
    public void listenUserInterface(SystemMessage sm) {
        FunctionType functionType = sm.getFunctionType();

        if (functionType == FunctionType.NONE) {
            return;
        }

        if (functionType == FunctionType.LOGIN) {
            UserInfo userInfo = (UserInfo)sm.getInput();
            this.login(userInfo);
            return;
        }

        if (functionType == FunctionType.LOGOUT) {
			this.logout();
            this.userInterface.setCurrentPage(PageType.LOGIN_PAGE);
        }


        if (functionType == FunctionType.EXIT) {
            try {
            	this.exit();
            }
        	catch (Exception e){
        	}
        }

        if (functionType == FunctionType.CHANGE_PAGE) {
            this.userInterface.setCurrentPage(sm.getNextPageType());
            return;

        }

        if (functionType == FunctionType.SELECT_COURSE ) {

			Student student = (Student) this.getCurrentUser();
			student.addSelectedCourse((Integer)sm.getInput());

            // handling of selecteable course data
            SelectableCoursesPage selectableCoursePage = (SelectableCoursesPage) this.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE);
			selectableCoursePage.setContent(this.pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectableCoursePage.setNumberOfSelectableCourses(student.getSelectableCourses().size());

            // handling selected course data
            SelectedCoursesPage selectedCoursePage = (SelectedCoursesPage) this.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE);
			selectedCoursePage.setContent(this.pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
			selectedCoursePage.setNumberOfDropableCourses(student.getSelectedCourses().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());

            return;

        }

        if (functionType == FunctionType.DROP_COURSE ) {
            Student student = (Student) this.getCurrentUser();

            student.dropCourse((Integer)sm.getInput());

            // handling selected course data
            SelectedCoursesPage selectedCoursePage = (SelectedCoursesPage) this.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE);
			selectedCoursePage.setContent(this.pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
			selectedCoursePage.setNumberOfDropableCourses(student.getSelectedCourses().size());


            // handling of selecteable course data
            SelectableCoursesPage selectableCoursePage = (SelectableCoursesPage) this.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE);
			selectableCoursePage.setContent(this.pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectableCoursePage.setNumberOfSelectableCourses(student.getSelectableCourses().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());

            return;

        }

        if (functionType == FunctionType.SEND_APPROVE ) {
            Student student = (Student) this.getCurrentUser();
            if (student.getRequest().equals("false")) {
                student.sendToApproval();
            }
            this.userInterface.setCurrentPage(sm.getNextPageType());
        }


        if (functionType == FunctionType.SELECET_STUDENT ) {
			Advisor advisor = (Advisor)this.getCurrentUser();
			advisor.selectStudent((Integer)sm.getInput());
			SelectedStudentRequestPage s = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
            s.setContent(pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));
			this.userInterface.setCurrentPage(sm.getNextPageType());
            return;
        }

        if (functionType == FunctionType.APPROVE_REQUEST ) {
			Advisor advisor = (Advisor)this.getCurrentUser();

			advisor.Approve();

            // handling selected student request
            SelectedStudentRequestPage selectedStdudentRequesPage = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
			selectedStdudentRequesPage.setContent(this.pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));

            // handling evaluate request
            EvaluateRequestsPage evaluateRequestPage = (EvaluateRequestsPage) this.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE);
			evaluateRequestPage.setContent(this.pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequestPage.setNumberOfRequest(advisor.getAwaitingStudents().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());

            return;

        }

        if (functionType == FunctionType.DISAPPREOVE_REQUEST ) {

			Advisor advisor = (Advisor)this.getCurrentUser();

			advisor.Disapprove();

            // handling selected student request
            SelectedStudentRequestPage selectedStdudentRequesPage = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
			selectedStdudentRequesPage.setContent(this.pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));

            // handling evaluate request
            EvaluateRequestsPage evaluateRequestPage = (EvaluateRequestsPage) this.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE);
			evaluateRequestPage.setContent(this.pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequestPage.setNumberOfRequest(advisor.getAwaitingStudents().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());
           
            return;
        }
    }

    public SystemDomain getDomain() {
        return domain;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
}

