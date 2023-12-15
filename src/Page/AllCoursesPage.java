package Page;

import System.*;

public class AllCoursesPage extends Page{
	
	public AllCoursesPage(String content) {
		super(content);
		setType(PageType.ALL_cOURSES_PAGE);
		setName("ALL COURSES PAGE");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, null);
	}
}