package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

public class MyStudentsPage extends Page {
	
	public MyStudentsPage(String content) {
		super(content);
		setType(PageType.MY_STUDENTS_PAGE);
		setName("All Students");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
	}
}
