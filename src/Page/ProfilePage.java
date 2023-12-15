package Page;

import System.FunctionType;
import System.SystemMessage;

public class ProfilePage extends Page{
	
	public ProfilePage(String content) {
		super(content);
		setType(PageType.PROFILE_PAGE);
		setName("Profile Page");
	}
	
	@Override 
	public SystemMessage runPage() {
		showContent();
		String input;
		while (true) {
			input = takeInput();
			if (input.equalsIgnoreCase("c")) {
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.CHANGE_PASSWORD_PAGE, null);
			}
			else if (input.equalsIgnoreCase("q")) {
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, null);
			}
			else {
				System.out.println("Wrong Input");
			}
		}
	}
}
