package Page;

import SystemMessage.SystemMessage;

public class ChangePasswaordPage extends Page{
	
	public ChangePasswaordPage(String content) {
		super(content);
		setType(PageType.CHANGE_PASSWORD_PAGE);
		setName("Change Password Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		
	// eklenecek
		
		return null;
	}
}
