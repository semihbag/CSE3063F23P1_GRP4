package pagePackage;

import systemMessagePackage.*;
import userInfoPackage.UserInfo;

public class LoginPage extends Page{
	private UserInfo userInfo;

	public LoginPage(String content) {
		super(content);
		setType(PageType.LOGIN_PAGE);
		setName("Login Page");
		userInfo = new UserInfo(null, null);
	}


	@Override
	public SystemMessage runPage() {
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
			
		return new SystemMessage(FunctionType.LOGIN, null,userInfo);
	}

	
	//////// GETTER - SETTET METHODS ///////////
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}	
}
