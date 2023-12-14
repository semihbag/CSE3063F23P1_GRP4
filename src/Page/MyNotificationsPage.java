package Page;

import SystemMessage.FunctionType;
import SystemMessage.SystemMessage;

public class MyNotificationsPage extends Page{
	
	public MyNotificationsPage(String content) {
		super(content);
		setType(PageType.MY_NOTIFICATIONS_PAGE);
		setName("My Notifications Page");
	}
	
	@Override
	public SystemMessage runPage() {
		showContent();
		takeInput();
		return new SystemMessage(FunctionType.READ_NOTIFICATIONS, PageType.MAIN_MENU_PAGE, null);
	}
}
