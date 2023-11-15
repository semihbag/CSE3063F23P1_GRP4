package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

public class EvaluateRequestsPage extends Page {
	
	public EvaluateRequestsPage(String content) {
		super(content);
		setType(PageType.EVALUATE_REQUESTS);
		setName("Evaluate Request Page");
	}

	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		
		
		
		
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
	}
}
