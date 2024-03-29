package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

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
		
		while(numberOfDropableCourses > 0) {
			input = takeInput();
			
			try {
				int selection = Integer.parseInt(input);

				if (selection > numberOfDropableCourses || selection < 0) {
					System.out.println("Enter a valid number");
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTED_COURSES_PAGE, null);
				}
				else {
					return new SystemMessage(FunctionType.DROP_COURSE, PageType.SELECTED_COURSES_PAGE, selection);
				}

			}
			catch (NumberFormatException e) {
				if (input.equals("a")) {
					return new SystemMessage(FunctionType.SEND_APPROVE, PageType.MAIN_MENU_PAGE_STUDENT, null);
				}
				
				if (input.equals("q")) {
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_STUDENT, null);
				}
				else {
					System.out.println("Wrong Input");
					return new SystemMessage(FunctionType.NONE, null, null);
				}

			}
		}
		
		input = takeInput();
		if (input.equals("q")) {
			return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_STUDENT, null);
		}
		else {
			System.out.println("Wrong Input");
			return new SystemMessage(FunctionType.NONE, null, null);
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
