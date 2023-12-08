package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class SelectableCoursesPage extends Page{
	
	private int numberOfSelectableCourses;
	
	public SelectableCoursesPage(String content) {
		super(content);
		setType(PageType.SELECTABLE_COURSES_PAGE);
		setName("Selectable Courses Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		String input;
		while(true) {		
			input = takeInput();
			try {
				int selection = Integer.parseInt(input);
				if (selection > numberOfSelectableCourses || selection < 0) {
					System.out.println("Enter a valid number");
				}
				else {
					return new SystemMessage(FunctionType.SELECT_COURSE, PageType.SELECTABLE_COURSES_PAGE, selection);
				}
			} 
			catch (NumberFormatException e) {
				if (input.equalsIgnoreCase("q")) {
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_STUDENT, null);
				}
				else {
					System.out.println("Wrong Input");
				}
			}	
		}		
	}
	
	/////////// GETTER - SETTER METHODS ////////////
	public int getNumberOfSelectableCourses() {
		return numberOfSelectableCourses;
	}

	public void setNumberOfSelectableCourses(int numberOfSelectableCourses) {
		this.numberOfSelectableCourses = numberOfSelectableCourses;
	}
}
