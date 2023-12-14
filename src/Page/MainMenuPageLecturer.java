package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class MainMenuPageLecturer extends Page{
	
	public MainMenuPageLecturer(String content) {
		super(content);
		setType(PageType.MAIN_MENU_PAGE);
		setName("Main Menu Page Lecturer");	
	}
	
	public SystemMessage runPage() {
		showContent();
		while (true) {
			switch (takeInput()) {
			case "1":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.PROFILE_PAGE_LECTURER, null);
			case "2":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_COURSES_PAGE, null);
			case "3":
				return new SystemMessage(FunctionType.LOGOUT, PageType.LOGIN_PAGE, null);
			case "4":
				return new SystemMessage(FunctionType.EXIT, null, null);
			default:
				System.out.println("Wrong Input!");
			}
		}
	}
}
