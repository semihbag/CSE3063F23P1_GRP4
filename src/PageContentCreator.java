public class PageContentCreator {

    public static String crateMainMenuPageStudentContent() 
	{
		String str="---------MAIN MENU---------\n"+
				    "1) All Courses\n"+
				    "2) Offered Courses\n"+
				    "3) Selected Courses\n"+
				    "4) Log out\n"+
				    "5) Exit";
		return str;
	}

    	public static String createMainMenuPageAdvisorContent() 
	{
		String str="---------MAIN MENU---------\n"+
				    "1) Advised Student Information\n"+
				    "2) View Course Requests\n"+
				    "3) Evaluate Requests\n"+
				    "4) Log out\n"+
				    "5) Exit\n";
		return str;
	}

}

