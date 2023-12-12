package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class ProfilePageAdvisor extends Page{
	
	public ProfilePageAdvisor(String content) {
		super(content);
		setType(PageType.PROFILE_PAGE_ADVISOR);
		setName("Profile Page Advisor");
		
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
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
			}
			else {
				System.out.println("Wrong Input");
			}
		}
	}
}
