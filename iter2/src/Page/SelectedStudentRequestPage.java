package Page;

import System.FunctionType;
import System.SystemMessage;

public class SelectedStudentRequestPage extends Page{
	
	public SelectedStudentRequestPage(String content) {
		super(content);
		setType(PageType.SELECTED_STUDENT_REQUEST_PAGE);
		setName("Selected Student Request Page");
	}

	@Override
	public SystemMessage runPage() {
		showContent();		
		String input;
		while (true) {
			input = takeInput();
			if (input.equalsIgnoreCase("a")) {
				System.out.println("Type your message or press enter directly:");
				String message = takeInput();
				return new SystemMessage(FunctionType.APPROVE_REQUEST, PageType.EVALUATE_REQUESTS_PAGE, message);
			}
			else if (input.equalsIgnoreCase("r")) {
				System.out.println("Type your message or press enter directly:");
				String message = takeInput();
				return new SystemMessage(FunctionType.DISAPPREOVE_REQUEST, PageType.EVALUATE_REQUESTS_PAGE, message);
			}
			else if (input.equalsIgnoreCase("q")) {
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.EVALUATE_REQUESTS_PAGE, null);
			}
			else {
				System.out.println("Wrong Input");
			}
		}
	}
}
