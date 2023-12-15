package Page;

import SystemMessage.*;
import UserInfo.UserInfo;

public class LoginPage extends Page{

	public LoginPage(String content) {
		super(content);
		setType(PageType.LOGIN_PAGE);
		setName("Login Page");
	}


	@Override
	public SystemMessage runPage() {
		
		// print the login page content to console
		showContent();

		String userInfo[] = new String[2];	

		// take password
		System.out.println("Username:");
		userInfo[0] = takeInput();
		
		// take password
		System.out.println("Password:");
		userInfo[1] = takeInput();

		return new SystemMessage(FunctionType.LOGIN, null,userInfo);
	}	
}
