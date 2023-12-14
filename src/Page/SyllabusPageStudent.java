package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class SyllabusPageStudent extends Page{
	
	public SyllabusPageStudent(String content) {
		super(content);
		setType(PageType.SYLLABUS_PAGE_STUDENT);
		setName("Syllabus Page Student");
	}

	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, null);
	}
}
