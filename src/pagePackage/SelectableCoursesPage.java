package pagePackage;

import java.util.ArrayList;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

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
		
		while(numberOfSelectableCourses > 0) {
			input = takeInput();
			
			try {
				int selection = Integer.parseInt(input);
				
				if (selection > numberOfSelectableCourses && selection < 0) {
					System.out.println("Enter a valid number");
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTABLE_COURSES_PAGE, null);
				}
				else {
					return new SystemMessage(FunctionType.SELECT_COURSE, PageType.SELECTABLE_COURSES_PAGE, selection);
				}
			} 
			catch (NumberFormatException e) {
				
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
	
	public int getNumberOfSelectableCourses() {
		return numberOfSelectableCourses;
	}

	public void setNumberOfSelectableCourses(int numberOfSelectableCourses) {
		this.numberOfSelectableCourses = numberOfSelectableCourses;
	}
}
