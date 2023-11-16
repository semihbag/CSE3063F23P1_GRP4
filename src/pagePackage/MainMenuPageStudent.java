package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

public class MainMenuPageStudent extends Page{
	
	public MainMenuPageStudent(String content) {
		super(content);
		setType(PageType.MAIN_MENU_PAGE_STUDENT);
		setName("Main Menu Page Student");
	}

	
	@Override
	public SystemMessage runPage() {
		showContent();
		
		switch (takeInput()) {
			case "1":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.ALL_cOURSES_PAGE, null);
			case "2":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTABLE_COURSES_PAGE, null);
			case "3":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTED_COURSES_PAGE, null);
			case "4":
				return new SystemMessage(FunctionType.LOGOUT, PageType.LOGIN_PAGE, null);
			case "5":
				return new SystemMessage(FunctionType.EXIT, null, null);
			default:
				System.out.println("Wrong Input!");
				return new SystemMessage(FunctionType.NONE, null, null);
		}
	}
}
