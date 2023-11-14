package pagePackage;

import systemMessagePackage.SystemMessage;

public class MainMenuPageStudent extends Page{

	public MainMenuPageStudent(String content) {
		super(content);
		setType(PageType.MAIN_MENU_PAGE);
		setName("Main Menu Page Student");
	}

	@Override
	public SystemMessage runPage() {
		showContent();

		switch (takeInput()) {
			case "1":
				System.out.println(1);
				break;
			case "2":
				System.out.println(2);
				break;
			case "3":
				System.out.println(3);
				break;
			default:
				System.out.println("aa");
		}
		return null;
	}
}
