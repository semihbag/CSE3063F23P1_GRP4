package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

public class MainMenuPageAdvisor extends Page {
	
	public MainMenuPageAdvisor(String content) {
		super(content);
		setType(PageType.MAIN_MENU_PAGE_ADVISOR);
		setName("Main Menu Page Advisor");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		
		switch (takeInput()) {
			case "1":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_STUDENTS_PAGE, null);
			case "2":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_REQUESTS_PAGE, null);
			case "3":
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.EVALUATE_REQUESTS_PAGE, null);
			case "4":
				return new SystemMessage(FunctionType.EXIT, null, null);
			default:
				System.out.println("Wrong Input!");
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
		}	
	}
}
