package pagePackage;

import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;

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

		while(numberOfRequest > 0) {
			input = takeInput();
			
			try {
				int selection = Integer.parseInt(input);

				if (selection > numberOfRequest || selection < 0) {
					System.out.println("Enter a valid number");
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.EVALUATE_REQUESTS_PAGE, null);
				}
				else {
					return new SystemMessage(FunctionType.SELECET_STUDENT, PageType.SELECTED_STUDENT_REQUEST_PAGE, selection);
				}
			}
			catch (NumberFormatException e) {
				if (input.equals("q")) {
					return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
				}
				else {
					System.out.println("Wrong Input");
					return new SystemMessage(FunctionType.NONE, null, null);
				}
			}
		}

		input = takeInput();
		if (input.equals("q")) {
			return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE_ADVISOR, null);
		}
		else {
			System.out.println("Wrong Input");
			return new SystemMessage(FunctionType.NONE, null, null);
		}
		
	}

	
	
	
	
	
	public int getNumberOfRequest() {
		return numberOfRequest;
	}

	public void setNumberOfRequest(int numberOfRequest) {
		this.numberOfRequest = numberOfRequest;
	}
}
