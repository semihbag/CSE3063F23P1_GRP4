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

    public static String courseListForContent (ArrayList<Course> courses) 
	{
		String str="";
		for (int i=1; i<=courses.size() ; i++ )
		{	   
			if(courses.get(i-1) instanceof CourseSession)
			{
				CourseSession session = (CourseSession) courses.get(i-1);
				str+= i+" -> "+courses.get(i-1).getCourseID().getId()
						+session.getSessionID().getId()
						+"|"+courses.get(i-1).getCourseName()
						+"|\n"+courses.get(i-1).getLecturer().getName()
						+"|\n"+courses.get(i-1).getQuota()+"\n"
						+"--------------------------------------"+"\n";	
			}
			else {
				str+= i+" -> "+courses.get(i-1).getCourseID().getId()
			+"|"+courses.get(i-1).getCourseName()
			+"|\n"+courses.get(i-1).getLecturer().getName()
			+"|\n"+courses.get(i-1).getQuota()+"\n"
			+"--------------------------------------"+"\n";	
			}
			
		}	
		return str;	
	}
    public static String createAllCoursesPageContent   (ArrayList<Course> courses) {
		String str="=>List of all courses<=\n"+
		"-------------------------------------\n"+
		courseListForContent(courses)+"\n"+
		"Press any key to back to main menu...";	
		return str;
		}

}

