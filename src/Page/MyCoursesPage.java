package Page;

import SystemMessage.SystemMessage;

public class MyCoursesPage extends Page{
	
	public MyCoursesPage(String content) {
		super(content);
		setType(PageType.MY_COURSES_PAGE);
		setName("My Courses Page");
	}
	
	@Override
	public SystemMessage runPage() {
		
		return null;
	}
}


