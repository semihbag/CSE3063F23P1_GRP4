package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class EvaluateRequestsPage extends Page {
	
	private int numberOfRequest;
	
	public EvaluateRequestsPage(String content) {
		super(content);
		setType(PageType.EVALUATE_REQUESTS_PAGE);
		setName("Evaluate Request Page");
	}

	@Override
	public SystemMessage runPage() {
		showContent();
		String input;
		while(true) {
			input = takeInput();
			try {
				int selection = Integer.parseInt(input);
				if (selection > numberOfRequest || selection < 0) {
					System.out.println("Enter a valid number");
				}
				else {
					return new SystemMessage(FunctionType.SELECET_STUDENT, PageType.SELECTED_STUDENT_REQUEST_PAGE, selection);
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
	
	public int getNumberOfRequest() {
		return numberOfRequest;
	}

	public void setNumberOfRequest(int numberOfRequest) {
		this.numberOfRequest = numberOfRequest;
	}
}
