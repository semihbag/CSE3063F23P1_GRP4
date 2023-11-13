package pagePackage;

import helper.ReGSystem;

public class ErrorPage extends Page{

	public ErrorPage(ReGSystem system, String cotent) {
		super(system, cotent);
		setType(PageType.ERROR_PAGE);
		setName("Error Page");
	}
	
	
	@Override
	public void runPage() {
		// TODO Auto-generated method stub
		showContent();
	}
	
}
