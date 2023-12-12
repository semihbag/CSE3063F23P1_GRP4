package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class ProfilePageStudent extends Page{
	
	public ProfilePageStudent(String content) {
		super(content);
		setType(PageType.PROFILE_PAGE_STUDENT);
		setName("Profile Page Student");
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
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_STUDENT, null);
			}
			else {
				System.out.println("Wrong Input");
			}
		}
	}
}
