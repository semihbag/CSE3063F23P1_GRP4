package helper;

import java.util.Scanner;

import pagePackage.EvaluateRequestsPage;
import pagePackage.MainMenuPageAdvisor;
import pagePackage.PageType;
import pagePackage.SelectableCoursesPage;
import pagePackage.SelectedCoursesPage;
import pagePackage.SelectedStudentRequestPage;
import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;
import userInfoPackage.UserInfo;
import userInterfacePackage.UserInterface;

public class ReGSystem {
	private UserInterface userInterface;
	
	public ReGSystem(UserInterface u) {
		this.userInterface = u;
		
	}
	
	public void run() {
		
		
		
		
		while (true) {
			userInterface.display();
			listenUserInterface(userInterface.getSystemMessage());
		}
		
	}
	
	public boolean login(UserInfo userInfo) {
		
		// true case
		//this.getUserInterface().setCurrentPage(PageType.MAIN_MENU_PAGE_STUDENT);
		this.getUserInterface().setCurrentPage(PageType.MAIN_MENU_PAGE_ADVISOR);
		return true;
		
		
		// false case
		//this.getUserInterface().setCurrentPage(PageType.LOGIN_PAGE);
		//return false;
	}
	
		
	
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
//			this.logout();
			this.userInterface.setCurrentPage(PageType.LOGIN_PAGE);
		}

		
		if (functionType == FunctionType.EXIT) {
			//this.exit(0);
		}
		
		if (functionType == FunctionType.CHANGE_PAGE) {
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}
		
		if (functionType == FunctionType.SELECT_COURSE ) {
			
//			Student student = (Student) this.getCurrentUser();
			
//			student.addSelectedCourse(sm.getInput());
	
			// handling of selecteable course data
			SelectableCoursesPage selectableCoursePage = (SelectableCoursesPage) this.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE);
//			selectableCoursePage.setContent(this.pageContentCreator.createSelectableCoursePageContent(student.getSelectableCourses());
//			selectableCoursePage.setNumberOfSelectableCourses(student.getSelectableCourses().size());
			
			// handling selected course data
			SelectedCoursesPage selectedCoursePage = (SelectedCoursesPage) this.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE);
//			selectedCoursePage.setContent(this.pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
//			selectedCoursePage.setNumberOfDropableCourses(student.getSelectedCourses().size());
	
			this.userInterface.setCurrentPage(sm.getNextPageType());

			
			
			
			// burada studentin içinden secilebilen derslere gidip gelen
			// input (index oluyor artık o) degerindeki course selected
			// listesine atacagız
			
			// ayrıca bu saatten sonra data değiştigi için sayfa contentini
			// tekrar ayarlamak lazım.
			// conten üreten fonksiyon (zenep yazıyor) tekrar cagırılıp üretildiği
			// string selecable page contentinin yeni değeri olacak
			// bu işlemi yapmazsak sürekli aynı görüntü print edilir
			// ayrıca sayfanın numberOdSelectableCourse değeri de güncellenmeli
			return;

		}
		
		if (functionType == FunctionType.DROP_COURSE ) {
			//Student student = (Student) this.getCurrentUser();
			
			//student.dropCourse(sm.getInput());
			
			// handling selected course data
			SelectedCoursesPage selectedCoursePage = (SelectedCoursesPage) this.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE);
//			selectedCoursePage.setContent(this.pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
//			selectedCoursePage.setNumberOfDropableCourses(student.getSelectedCourses().size());
				
			
			// handling of selecteable course data
			SelectableCoursesPage selectableCoursePage = (SelectableCoursesPage) this.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE);
//			selectableCoursePage.setContent(this.pageContentCreator.createSelectableCoursePageContent(student.getSelectableCourses());
//			selectableCoursePage.setNumberOfSelectableCourses(student.getSelectableCourses().size());
						
			this.userInterface.setCurrentPage(sm.getNextPageType());

			
			
			
			// burada studentin içinden secilen derslere gidip gelen
			// input (index oluyor artık o) degerindeki course drop işlemi yapıcaz
			
			// ayrıca bu saatten sonra data değiştigi için sayfa contentini
			// tekrar ayarlamak lazım.
			// conten üreten fonksiyon (zenep yazıyor) tekrar cagırılıp üretildiği
			// string selected page contentinin yeni değeri olacak
			// bu işlemi yapmazsak sürekli aynı görüntü print edilir.
			// ayrıca sayfanın numberOdDropableCourse değeri de güncellenmeli
			return;
			
		}

		if (functionType == FunctionType.SELECET_STUDENT ) {
//			Advisor advisor = (Advisor)this.getCurrentUser();
			
//			advisor.selectStudent(sm.getInput);
			
			
			// burada advisorun select fonksiyonu çağırılacak parametre olarak sm.getInput() verilecek
			
			
			
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}

		if (functionType == FunctionType.APPROVE_REQUEST ) {
//			Advisor advisor = (Advisor)this.getCurrentUser();

//			advisor.Approve();

			// handling selected student request
			SelectedStudentRequestPage selectedStdudentRequesPage = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
//			selectedStdudentRequesPage.setContent(this.pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));
			
			// handling evaluate request 
			EvaluateRequestsPage evaluateRequestPage = (EvaluateRequestsPage) this.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE);
//			evaluateRequestPage.setContent(this.pageContentCreator(advisor.getAwaitingStudents()));
//			evaluateRequestPage.setNumberOfRequest(advisor.getAwaitingStudents(.size()));
			
			
			// burada seçilen req için onaylama istegi geliyor burada onaylamamız lazım.
			// ayrıca artık req sayısı değiştiği için EVALUATE_REQUESTS_PAGE sayfası contenti güncellenmeli
			// ayrıca SELECTED_STUDENT_REQUEST_PAGE contenti de değişecek
			// zenepin yazdıgı fonk tekrar cağırılacak ve settet ile content degiştirilecek
			
		
			System.out.println("---req approved");
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}

		if (functionType == FunctionType.DISAPPREOVE_REQUEST ) {
			
//			Advisor advisor = (Advisor)this.getCurrentUser();

//			advisor.Disapprove();
			
			// handling selected student request
			SelectedStudentRequestPage selectedStdudentRequesPage = (SelectedStudentRequestPage) this.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE);
//			selectedStdudentRequesPage.setContent(this.pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));	

			// handling evaluate request 
			EvaluateRequestsPage evaluateRequestPage = (EvaluateRequestsPage) this.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE);
//			evaluateRequestPage.setContent(this.pageContentCreator(advisor.getAwaitingStudents()));
//			evaluateRequestPage.setNumberOfRequest(advisor.getAwaitingStudents(.size()));
			
			
			
			
			
			
			
			// burada secilen req istegi reddedildi
			// yani listeden kaldırldı
			// yine yıkarıda oldugu gibi EVALUATE_REQUESTS_PAGE contenti değişecek
			// ayrıca SELECTED_STUDENT_REQUEST_PAGE contenti de değişecek
			// zenepin yazdıgı fonk tekrar cağırılacak ve settet ile content degiştirilecek

		
			System.out.println("---req disapproved");
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;
		}
	}
	
	
	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	
	
	
	
}
