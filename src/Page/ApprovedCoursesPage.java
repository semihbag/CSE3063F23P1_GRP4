package Page;

import System.FunctionType;
import System.SystemMessage;

public class ApprovedCoursesPage extends Page{

	public ApprovedCoursesPage(String content) {
		super(content);
		setType(PageType.APPROVED_COURSES_PAGE);
		setName("Approved Courses Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, null);
	}
}
