package helper;

import java.util.Scanner;

import userInfoPackage.UserInfo;
import userInterfacePackage.UserInterface;

public class ReGSystem {
	private UserInterface userInterface;
	private Scanner scanner;
	
	public ReGSystem(UserInterface u) {
		this.userInterface = u;
		scanner = new Scanner(System.in);
		
	}
	
	public boolean login(UserInfo userInfo) {
		return true;
	}
	
	
	
	

	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	
	
}
