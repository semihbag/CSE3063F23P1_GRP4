package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

public class MyRequestPage extends Page {
	
	public MyRequestPage(String content) {
		super(content);
		setType(PageType.MY_REQUESTS);
		setName("My Request");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
	}
}
