package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

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

			if (input.toLowerCase().equals("y")) {
				return new SystemMessage(FunctionType.APPROVE_REQUEST, PageType.EVALUATE_REQUESTS_PAGE, null);
			}
			
			if (input.toLowerCase().equals("r")) {
				return new SystemMessage(FunctionType.DISAPPREOVE_REQUEST, PageType.EVALUATE_REQUESTS_PAGE, null);
			}
			
			if (input.toLowerCase().equals("q")) {
				return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.EVALUATE_REQUESTS_PAGE, null);
			}
			else {
				System.out.println("Wrong Input");
				continue;
			}
		}
	}
}
