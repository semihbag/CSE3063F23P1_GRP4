package helper;

import java.util.Scanner;

import pagePackage.PageType;
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
		this.getUserInterface().setCurrentPage(PageType.MAIN_MENU_PAGE);
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
		
		if (functionType == FunctionType.EXIT) {
			// exit
		}
		
		if (functionType == FunctionType.CHANGE_PAGE) {
			this.userInterface.setCurrentPage(sm.getNextPageType());
		}
		
		if (functionType == FunctionType.SELECT_COURSE ) {
			
		}

	}
	
	
	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	
	
	
	
}
