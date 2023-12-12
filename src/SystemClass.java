// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import SystemMessage.*;
import UserInfo.*;
import UserInterface.*;
import Page.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
		LoginPage login = new LoginPage("Welcome! Please enter your username/password.");
		userInterface.addPage(login);
		userInterface.setCurrentPage(PageType.LOGIN_PAGE);
    	domain = new SystemDomain();
    }

    //Start running the code
	public void run() throws JSONException, IOException {
		while (true) {
			userInterface.display();
			listenUserInterface(userInterface.getSystemMessage());
		}
	}

    //Login with the given user info
    public void login(UserInfo userInfo) {
        boolean userFound = false;
        if (userInfo.getUsername().charAt(0) == 'o') {
            for (Student student : domain.getStudents()) {
                if (("o" + student.getStudentId().getId()).equals(userInfo.getUsername()) &&
                        student.getPassword().getPassword().equals(userInfo.getPassword())) {
                    setCurrentUser(student);
                    userFound = true;
                    userInterface.setPages(domain.createPages(currentUser));
                    userInterface.setCurrentPage(PageType.MAIN_MENU_PAGE_STUDENT);
                    System.out.println("\u001B[32;1mLOGIN SUCCESSFUL - WELCOME " + currentUser.getFirstName() + " " + currentUser.getLastName() + "\u001B[0m");
                    break;
                }
            }
        } else if (userInfo.getUsername().charAt(0) == 'a') {
            for (Advisor advisor : domain.getAdvisors()) {
                if (("a" + advisor.getLecturerId().getId()).equals(userInfo.getUsername()) &&
                        advisor.getPassword().getPassword().equals(userInfo.getPassword())) {
                    advisor.findAwaitingStudents();
                	setCurrentUser(advisor);
                    userFound = true;
                    userInterface.setPages(domain.createPages(currentUser));
                    userInterface.setCurrentPage(PageType.MAIN_MENU_PAGE_ADVISOR);
                    System.out.println("\u001B[32;1mLOGIN SUCCESSFUL - WELCOME " + currentUser.getFirstName() + " " + currentUser.getLastName() + "\u001B[0m");
                    break;
                }
            }
        } if (!userFound) {
        	System.out.println("\u001B[33;1mUsername/Password incorrect.\n\u001B[0m");
        }
    }

    //Logout from an account
    public void logout() {
    	userInterface.resetPages();
    	LoginPage login = new LoginPage("Welcome! Please enter your username/password.");
		userInterface.addPage(login);
		userInterface.setCurrentPage(PageType.LOGIN_PAGE);
        setCurrentUser(null);
    }

    //Exit the system
    public void exit() throws JSONException, IOException {
        updateStudentJSON();
        updateCourseJSON();
        System.exit(0);
    }

    //Update course quota after selections in JSON files
    public void updateCourseJSON() throws IOException, JSONException {
        Path path = Path.of("src\\JSON_Files\\courses.json");
        String content = new String(Files.readAllBytes(path));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");
        for(int i = 0; i < courseJSON.length(); i++){
            JSONObject currentCourse = courseJSON.getJSONObject(i);
            currentCourse.put("quota",domain.getCourses().get(i).getQuota());
            currentCourse.put("studentList",studentToJsonArray(domain.getCourses().get(i).getStudentList()));
        }
        Files.write(path,jsonObject.toString(4).getBytes(),StandardOpenOption.TRUNCATE_EXISTING);
    }


    //Update student infos in JSON files
    public void updateStudentJSON() throws JSONException, IOException {
        for (int i = 0; i < domain.getStudents().size(); i++) {
            String studentId = domain.getStudents().get(i).getStudentId().getId();
            Path path = Path.of("src\\JSON_Files\\Students\\" + studentId + ".json");
            String content = new String(Files.readAllBytes(path));
            JSONObject jsonStudent = new JSONObject(content);
            JSONObject registration = jsonStudent.getJSONObject("registration");
            ArrayList<Course> selected = domain.getStudents().get(i).getSelectedCourses();
            ArrayList<Course> approved = domain.getStudents().get(i).getApprovedCourses();
            registration.put("selectedcourses",transcriptCourses(selected));
            registration.put("approvedcourses", transcriptCourses(approved));
            jsonStudent.put("request", domain.getStudents().get(i).getRequest());
            jsonStudent.put("readNotification", domain.getStudents().get(i).getReadNotifications().toArray(new String[0]));
            jsonStudent.put("unreadNotification", domain.getStudents().get(i).getUnreadNotifications().toArray(new String[0]));
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
            if (transcriptCoursesList.get(i) instanceof CourseSession crsSession) {
                transcriptCoursesAr[i] = crsSession.getCourseId().getId() + "." + crsSession.getSessionId().getId();
            } else {
                transcriptCoursesAr[i] = transcriptCoursesList.get(i).getCourseId().getId();
            }
        } return transcriptCoursesAr;
    }

    //PAGE MERGE WITH SYSTEM PROCESSES
    public void listenUserInterface(SystemMessage sm) throws JSONException, IOException {
        FunctionType functionType = sm.getFunctionType();
        if (functionType == FunctionType.LOGIN) {
            UserInfo userInfo = (UserInfo)sm.getInput();
            this.login(userInfo);
        }
        else if (functionType == FunctionType.LOGOUT) {
			this.logout();
	        System.out.println("\u001B[31;1mLOGOUT SUCCESSFUL - GOODBYE"+ currentUser.getFirstName() + " " + currentUser.getLastName() + "\u001B[0m");
            this.userInterface.setCurrentPage(PageType.LOGIN_PAGE);
        }
        else if (functionType == FunctionType.EXIT) {
            try {
    	        System.out.println("\u001B[31;1mSYSTEM EXITING\u001B[0m");
            	Thread.sleep(500);
    	        System.out.println("\u001B[31;1mSYSTEM EXITING.\u001B[0m");
            	Thread.sleep(500);
    	        System.out.println("\u001B[31;1mSYSTEM EXITING..\u001B[0m");
            	Thread.sleep(500);
    	        System.out.println("\u001B[31;1mSYSTEM EXITING...\u001B[0m");
            }
            catch (Exception e){	
            }
            finally {
                exit();
            }
        }
        else if (functionType == FunctionType.CHANGE_PAGE) {
            this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.SELECT_COURSE ) {
			Student student = (Student) this.getCurrentUser(); 
			String courseName = student.getSelectableCourses().get((Integer)sm.getInput()).getCourseName();
			
			if (student.addSelectedCourse((Integer)sm.getInput())) {
                System.out.println("\u001B[32;1mCourse Addition Is Succesful - " + courseName + "\u001B[0m");

			}
			else {
    	        System.out.println("\u001B[33;1mCourse Addition Is Not Succesful - " + courseName + "\u001B[0m");
			}

            // handling of selecteable course data
            SelectableCoursesPage selectableCoursePage = (SelectableCoursesPage) this.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE);
			selectableCoursePage.setContent(this.pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectableCoursePage.setNumberOfSelectableCourses(student.getSelectableCourses().size());

            // handling selected course data
            SelectedCoursesPage selectedCoursePage = (SelectedCoursesPage) this.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE);
			selectedCoursePage.setContent(this.pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
			selectedCoursePage.setNumberOfDropableCourses(student.getSelectedCourses().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.DROP_COURSE ) {
            Student student = (Student) this.getCurrentUser();
			String courseName = student.getSelectedCourses().get((Integer)sm.getInput()).getCourseName();

            student.dropCourse((Integer)sm.getInput());
            System.out.println("\u001B[32;1mCourse Dropping Is Succesful - " + courseName + "\u001B[0m");

            // handling selected course data
            SelectedCoursesPage selectedCoursePage = (SelectedCoursesPage) this.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE);
			selectedCoursePage.setContent(this.pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
			selectedCoursePage.setNumberOfDropableCourses(student.getSelectedCourses().size());

            // handling of selecteable course data
            SelectableCoursesPage selectableCoursePage = (SelectableCoursesPage) this.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE);
			selectableCoursePage.setContent(this.pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectableCoursePage.setNumberOfSelectableCourses(student.getSelectableCourses().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.SEND_APPROVE ) {
            Student student = (Student) this.getCurrentUser();
            if (student.getRequest().equals("false")) {
                student.sendToApproval();
                System.out.println("\u001B[32;1mYou have successfully sent your course selection list for advisor approval!\u001B[0m");
            }
            else {
                System.out.println("\u001B[33;1mYou have already successfully sent your course selection list for advisor approval!\u001B[0m");
            }
            this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.SELECET_STUDENT ) {
			Advisor advisor = (Advisor)this.getCurrentUser();
			advisor.selectStudent((Integer)sm.getInput());
			
			// handling selected request 
			SelectedStudentRequestPage s = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
            s.setContent(pageContentCreator.createSelectedStudentsRequestPageContent(advisor.getSelectStudent()));
			this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.APPROVE_REQUEST ) {
			Advisor advisor = (Advisor)this.getCurrentUser();
			String selectedStudentFullName = advisor.getSelectStudent().getFirstName() + " " + advisor.getSelectStudent().getLastName();
 			advisor.approve();
            
 			System.out.println("\u001B[32;1mRequest Has Been Approved - " + selectedStudentFullName + "'s Request\u001B[0m");

            // handling selected student request
            SelectedStudentRequestPage selectedStdudentRequesPage = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
			selectedStdudentRequesPage.setContent(this.pageContentCreator.createSelectedStudentsRequestPageContent(advisor.getSelectStudent()));

            // handling evaluate request
            EvaluateRequestsPage evaluateRequestPage = (EvaluateRequestsPage) this.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE);
			evaluateRequestPage.setContent(this.pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequestPage.setNumberOfRequest(advisor.getAwaitingStudents().size());
            this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.DISAPPREOVE_REQUEST ) {
			Advisor advisor = (Advisor)this.getCurrentUser();
			String selectedStudentFullName = advisor.getSelectStudent().getFirstName() + " " + advisor.getSelectStudent().getLastName();
			advisor.disapprove();

 			System.out.println("\u001B[33;1mRequest Has Been Disapproved - " + selectedStudentFullName + "'s Request\u001B[0m");

            // handling selected student request
            SelectedStudentRequestPage selectedStdudentRequesPage = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
			selectedStdudentRequesPage.setContent(this.pageContentCreator.createSelectedStudentsRequestPageContent(advisor.getSelectStudent()));

            // handling evaluate request
            EvaluateRequestsPage evaluateRequestPage = (EvaluateRequestsPage) this.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE);
			evaluateRequestPage.setContent(this.pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequestPage.setNumberOfRequest(advisor.getAwaitingStudents().size());

            this.userInterface.setCurrentPage(sm.getNextPageType());
        }
        else if (functionType == FunctionType.SELECT_MY_COURSE ) {
			Lecturer lecturer = (Lecturer)this.getCurrentUser();
			lecturer.selectCourse((Integer)sm.getInput());
			
			// handling selected my course
			SelectedMyCoursePage selectedMyCourse = (SelectedMyCoursePage) this.userInterface.selectPage(PageType.SELECTED_MY_COURSE_PAGE);
			selectedMyCourse.setContent(this.pageContentCreator.createSelectedMyCoursePage(lecturer.getSelectedCourse()));
			
			this.userInterface.setCurrentPage(sm.getNextPageType());			
        }
        else if (functionType == FunctionType.CHECK_PASSWORD ) {
        	////// eklenecek
        }
        else if (functionType == FunctionType.CHANGE_PASSWORD ) {
        	////// eklenecek
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

