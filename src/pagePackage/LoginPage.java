package pagePackage;

import java.util.Scanner;

import userInfoPackage.UserInfo;

public class LoginPage extends Page{
	public UserInfo userInfo;

	public LoginPage(Scanner consoleScanner, PageType type, String name, String content) {
		super(consoleScanner, content);
		setType(PageType.LOGIN_PAGE);
		setName("Login Page");
		userInfo = new UserInfo(null, null);
	}


	@Override
	public void runPage() {
		// set user info null because this func may not run one time
		userInfo.reset();
		
		// print the login page content to console
		showContent();
		
		// take password
		System.out.println("Username:");
		userInfo.setUsername(takeInput());
		
		// take password
		System.out.println("Password:");
		userInfo.setPassword(takeInput());
			
		
		/// burası halledilecek
		boolean res = true;
		//res = system.login(userInfo);		
		
		if (res) {
			// system.getUserInterface.setCurrentPage(PageType.MAIN_MENU_PAGE);
		}
		else {
			// system.getUserInterface.setCurrentPage(PageType.ERROR_PAGE);
		}
	}
	
}
