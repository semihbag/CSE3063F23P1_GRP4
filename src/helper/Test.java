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
		Page login = new LoginPage(loginCont);
		
		String mainStudentCont = "1- See all courses \n2- See selectable courses \n3- See selected courses \n4- Exit";
		MainMenuPageStudent mainStudent = new MainMenuPageStudent(mainStudentCont);
		
		String allCoursesCont = "==>List of all courses<==\n1- cse3131 \n2- cse6969 \n3- cse3169 \n\nPress any key to back main menu...";
		AllCoursesPage allCourses = new AllCoursesPage(allCoursesCont);

		String selectabbleCourseCont = "==>List of all selectable courses<=="
				+ "\nPress number to select course, "
				+ "\nPress y to complete your selection,"
				+ "\nPress q to exit."
				+ "\n\n1- cse3131 \n2- cse6969 \n3- cse3169 \n\n"
				+ "selected courses:"
				+ "\n1- EE2055";
		SelectableCoursesPage selectable = new SelectableCoursesPage(selectabbleCourseCont);
		selectable.setNumberOfSelectableCourses(3);
		
		ui.addPage(login);
		ui.addPage(mainStudent);
		ui.addPage(allCourses);
		ui.addPage(selectable);
		
		ui.setCurrentPage(PageType.LOGIN_PAGE);
		
		
		system.run();
		

		
		
		
	
		
	
		
		
		
		System.err.println("\ntest basarili");
	}

}
