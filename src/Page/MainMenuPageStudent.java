package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class MainMenuPageStudent extends Page{
	
	public MainMenuPageStudent(String content) {
		super(content);
		setType(PageType.MAIN_MENU_PAGE);
		setName("Main Menu Page PersonObject.Student");
	}

	@Override
	public SystemMessage runPage() {
		showContent();
		while (true) {
			switch (takeInput()) {
			case "1":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.PROFILE_PAGE, null);
			case "2":
				return new SystemMessage(FunctionType.READ_NOTIFICATIONS, PageType.MY_NOTIFICATIONS_PAGE, null);
			case "3":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.TRANSCRIPT_PAGE, null);
			case "4":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SYLLABUS_PAGE, null);
			case "5":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.ALL_cOURSES_PAGE, null);
			case "6":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.APPROVED_COURSES_PAGE, null);
			case "7":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTABLE_COURSES_PAGE, null);
			case "8":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTED_COURSES_PAGE, null);
			case "9":
				return new SystemMessage(FunctionType.LOGOUT, PageType.LOGIN_PAGE, null);
			case "10":
				return new SystemMessage(FunctionType.EXIT, null, null);
			default:
				System.out.println("Wrong Input!");
			}
		}
	}
}
