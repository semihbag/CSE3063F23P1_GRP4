package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class TranscriptPage extends Page {
	
	public TranscriptPage(String content) {
		super(content);
		setType(PageType.TRANSCRIPT_PAGE);
		setName("My Transcript Page");
	}
	
	@Override 
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, null);
	}
}
