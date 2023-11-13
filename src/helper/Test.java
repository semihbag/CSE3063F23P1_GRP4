package helper;

import java.util.Scanner;
import pagePackage.*;

import userInterfacePackage.*;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterface ui = new UserInterface();
		ReGSystem system = new ReGSystem(ui);
		
				
		String loginCont = "kurs kayıt sistemine hoşgeldiniz lütfen username ve password giriniz";
		LoginPage login = new LoginPage(system, loginCont);
		
		String errorCont = "hatali input girisi";
		ErrorPage error = new ErrorPage(system, errorCont);
		
		ui.addPage(login);
		ui.addPage(error);
		System.out.println("zort");
		ui.setCurrentPage(PageType.LOGIN_PAGE);
		
		
		
		ui.display();
		ui.display();
		
	
		
		
		
		system.getScanner().close();
		System.out.println("test basarili");
	}

}
