package Page;

import System.FunctionType;
import System.SystemMessage;

public class ChangePasswaordPage extends Page{
	
	public ChangePasswaordPage(String content) {
		super(content);
		setType(PageType.CHANGE_PASSWORD_PAGE);
		setName("Change Password Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		String passwords[] = new String[2];	
		System.out.println("Enter your current password: ");
		passwords[0] = takeInput();
		System.out.println("Enter your new password");
		passwords[1] = takeInput();
	
		return new SystemMessage(FunctionType.CHANGE_PASSWORD, PageType.PROFILE_PAGE, passwords);
	}
}
