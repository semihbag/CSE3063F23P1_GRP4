package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class SelectedCoursesPage extends Page{
	
	private int numberOfDropableCourses;
	
	public SelectedCoursesPage(String content) {
		super(content);
		setType(PageType.SELECTED_COURSES_PAGE);
		setName("Selected Courses Page");
		
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		String input;
		
		while(true) {
			input = takeInput();
			
			try {
				int selection = Integer.parseInt(input);

				if (selection > numberOfDropableCourses || selection < 0) {
					System.out.println("Enter a valid number");
					continue;
				}
				else {
					return new SystemMessage(FunctionType.DROP_COURSE, PageType.SELECTED_COURSES_PAGE, selection);
				}

			}
			catch (NumberFormatException e) {
				if (input.toLowerCase().equals("a")) {
					return new SystemMessage(FunctionType.SEND_APPROVE, PageType.MAIN_MENU_PAGE_STUDENT, null);
				}
				
				if (input.toLowerCase().equals("q")) {
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_STUDENT, null);
				}
				else {
					System.out.println("Wrong Input");
					continue;
				}
			}
		}		
	}

	
	/////////// GETTER - SETTER METHODS ////////////
	
	public int getNumberOfDropableCourses() {
		return numberOfDropableCourses;
	}

	public void setNumberOfDropableCourses(int numberOfDropableCourses) {
		this.numberOfDropableCourses = numberOfDropableCourses;
	}
}
