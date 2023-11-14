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
		ArrayList<Integer> selectedNumbers = new ArrayList<>();
		
		while(true) {
			input = takeInput();
			
			try {
				int selection = Integer.parseInt(input);
				
				if (selection > numberOfSelectableCourses) {
					System.out.println("Enter a valid number");
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTABLE_COURSES_PAGE, null);
				}
				
				if (selectedNumbers.contains(selection)) {
					System.out.println("You have already chosen this course");
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.SELECTABLE_COURSES_PAGE, null);
				}
				else {
					selectedNumbers.add(selection);
					return new SystemMessage(FunctionType.SELECT_COURSE, PageType.SELECTABLE_COURSES_PAGE, selection);
				}
			} 
			catch (NumberFormatException e) {

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
