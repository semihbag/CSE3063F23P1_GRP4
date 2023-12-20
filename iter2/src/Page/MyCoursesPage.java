package Page;

import System.FunctionType;
import System.SystemMessage;

public class MyCoursesPage extends Page{
	
	private int numberOfCourses;
	
	public MyCoursesPage(String content) {
		super(content);
		setType(PageType.MY_COURSES_PAGE);
		setName("My Courses Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();	
		String input;
		while(true) {
			input = takeInput();
			try {
				int selection = Integer.parseInt(input);
				if (selection > numberOfCourses || selection < 0) {
					System.out.println("Enter a valid number");
				}
				else {
					return new SystemMessage(FunctionType.SELECT_MY_COURSE, PageType.SELECTED_MY_COURSE_PAGE, selection);
				}
			}
			catch (NumberFormatException e) {
				if (input.equalsIgnoreCase("q")) {
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, null);
				}
				else {
					System.out.println("Wrong Input");
				}
			}
		}
	}

	public int getNumberOfCourses() {
		return numberOfCourses;
	}

	public void setNumberOfCourses(int numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}
}


