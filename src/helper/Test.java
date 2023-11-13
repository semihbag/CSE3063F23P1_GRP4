package helper;

import java.util.Scanner;
import pagePackage.*;

import userInterfacePackage.*;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterface ui = new UserInterface();
		ReGSystem system = new ReGSystem(null);
		
		
		
		Scanner scanner = new Scanner(System.in);
		String content = "kurs kayıt sistemine hoşgeldiniz lütfen username ve password giriniz";
		LoginPage login = new LoginPage(system, content);
		
		boolean a = ui.addPage(login);
		System.out.println(a);
		
		boolean b = ui.setCurrentPage(PageType.LOGIN_PAGE);
		System.out.println(b);
		
		ui.display();
		
	
		System.out.println("test basarili");
	}

}
