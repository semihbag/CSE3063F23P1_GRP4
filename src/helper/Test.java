package helper;

import java.util.Scanner;
import pagePackage.*;

import userInterfacePackage.*;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterface ui = new UserInterface();
		ReGSystem system = new ReGSystem(ui);
		
		
		String loginCont = "welcome system!";
		LoginPage login = new LoginPage(loginCont);
		
		
		String mainStudentCont = 
				"*** MAIN MENU STUDENT ***"
				+ "\n1- See all courses "
				+ "\n2- See selectable courses "
				+ "\n3- See selected courses "
				+ "\n4- See approved courses "
				+ "\n5- Exit"
				+ "\n";
		MainMenuPageStudent mainStudent = new MainMenuPageStudent(mainStudentCont);
		
		
		String allCoursesCont = 
				"==>List of all courses<=="
				+ "\n1- cse3131 "
				+ "\n2- cse6969 "
				+ "\n3- cse3169 "
				+ "\n\nPress any key to back main menu...";
		AllCoursesPage allCourses = new AllCoursesPage(allCoursesCont);

		
		String selectableCourseCont = 
				"==>List of all selectable courses<=="
				+ "\nPress number to select course, "
				+ "\nPress q to exit\n"
				+ "\n1- cse3131 "
				+ "\n2- cse6969 "
				+ "\n3- cse3169 \n"
				+ "\nselected courses:"
				+ "\n1- EE2055";
		SelectableCoursesPage selectable = new SelectableCoursesPage(selectableCourseCont);
		selectable.setNumberOfSelectableCourses(3);
		
		
		String selectedCourseCont =
				"==List of all selected courses<=="
				+ "\nPress number to drop course, "
				+ "\nPress q to exit\n"
				+ "\n1- cse3131 "
				+ "\n2- cse6969 "
				+ "\n3- cse3169\n"
				+ "\ndropped courses:"
				+ "\n1- EE2055";
		SelectedCoursesPage selected = new SelectedCoursesPage(selectedCourseCont);
		selected.setNumberOfDropableCourses(3);
		
		
		String approvedCourseCont = 
				"==>List of all approved courses<==\n"
				+ "\n1- cse3131 "
				+ "\n2- cse6969 "
				+ "\n3- cse3169"
				+ "\n\nPress any key to back main menu...";
		ApprovedCoursesPage approved = new ApprovedCoursesPage(approvedCourseCont);
		
		//___________________________________________________________________________________________
		

		String mainAdvisorCont = 
				"*** MAIN MENU ADVISOR ***"
				+ "\n1- See all my students "
				+ "\n2- See all my request "
				+ "\n3- Evaluate request "
				+ "\n4- Exit"
				+ "\n";
		MainMenuPageAdvisor mainAdvisor = new MainMenuPageAdvisor(mainAdvisorCont);
		
		
		String myStudentsCont = 
				"==>List of all my students<=="
				+ "\n1- semih "
				+ "\n2- zenep "
				+ "\n3- nidda "
				+ "\n4- cece "
				+ "\n\nPress any key to back main menu...";
		MyStudentsPage myStudents = new MyStudentsPage(myStudentsCont);
		
	
		String myRequestsCont = 
				"==>List of all my request<=="
				+ "\n1- semih -> cse3131, ee5555"
				+ "\n2- zenep -> cse6634, math1021, ee2021"
				+ "\n3- nidda -> cse6969, ata1000"
				+ "\n4- cece -> cse2121, ger5500, ger3300, stat2031"
				+ "\n\nPress any key to back main menu...";
		MyRequestPage myRequests = new MyRequestPage(myRequestsCont);
		
		
		String evaluateRequestCont =
				"==>Evaluate requests"
				+ "\nPress number to select student and requset, "
				+ "\nPress q to exit\n"
				+ "\n1- Semih - reuest of semih"
				+ "\n2- Cece - request of cece"
				+ "\n3- ali - request of ali";
		EvaluateRequestsPage evaluateRequest = new EvaluateRequestsPage(evaluateRequestCont);
		evaluateRequest.setNumberOfRequst(3);
		
		String selectedStudentRequestCont = 
				"==>Request Of Selected Student Page"
				+ "\n Name: Ali"
				+ "\n Surname: Veli"
				+ "\n\nCourses:"
				+ "\n1-cse2232"
				+ "\n2-cse3444"
				+ "\n\nPress y to approve,"
				+ "\nPress n to disapprove"
				+ "\nPress q to exit";
		SelectedStudentRequestPage selectedStudentRequest = new SelectedStudentRequestPage(selectedStudentRequestCont);
		
		
		
		
		
		ui.addPage(login);
		ui.addPage(mainStudent);
		ui.addPage(allCourses);
		ui.addPage(selectable);
		ui.addPage(selected);
		ui.addPage(approved);
		
		ui.addPage(mainAdvisor);
		ui.addPage(myStudents);
		ui.addPage(myRequests);
		ui.addPage(evaluateRequest);
		ui.addPage(selectedStudentRequest);

		
		ui.setCurrentPage(PageType.LOGIN_PAGE);
		
		
		system.run();
		

		
		
		
	
		
	
		
		
		
		System.err.println("\ntest basarili");
	}

}
