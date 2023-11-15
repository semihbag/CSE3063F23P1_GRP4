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
				"==>List of all students<=="
				+ "\n1- semih "
				+ "\n2- zenep "
				+ "\n3- nidda "
				+ "\n4- cece "
				+ "\n\nPress any key to back main menu...";
		MyStudentsPage myStudents = new MyStudentsPage(myStudentsCont);
		
		
		ui.addPage(login);
		ui.addPage(mainStudent);
		ui.addPage(allCourses);
		ui.addPage(selectable);
		ui.addPage(selected);
		ui.addPage(approved);
		
		ui.addPage(mainAdvisor);
		ui.addPage(myStudents);

		
		ui.setCurrentPage(PageType.LOGIN_PAGE);
		
		
		system.run();
		

		
		
		
	
		
	
		
		
		
		System.err.println("\ntest basarili");
	}

}
