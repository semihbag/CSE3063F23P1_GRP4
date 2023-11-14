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
		
		String mainStudentCont = "1- See all courses \n2- See selectable courses \n3- See selected courses";
		MainMenuPageStudent mainStudent = new MainMenuPageStudent(mainStudentCont);
		
		ui.addPage(login);
		ui.addPage(mainStudent);
		ui.setCurrentPage(PageType.LOGIN_PAGE);
		
		
		system.run();
		

		
		
		
	
		
	
		
		
		
		System.err.println("\ntest basarili");
	}

}
