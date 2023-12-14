package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class SelectedMyCoursePage extends Page{
	
	public SelectedMyCoursePage(String content) {
		super(content);
		setType(PageType.SELECTED_MY_COURSE_PAGE);
		setName("Selected My Course Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_COURSES_PAGE, null);
	}
}
