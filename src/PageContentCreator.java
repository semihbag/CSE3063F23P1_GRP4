import java.util.ArrayList;

public class PageContentCreator {

	public  String crateMainMenuPageStudentContent()
	{
		String str="---------MAIN MENU---------\n"+
				"1) All Courses\n"+
				"2) Offered Courses\n"+
				"3) Selected Courses\n"+
				"4) Log out\n"+
				"5) Exit";
		return str;
	}

	public  String createMainMenuPageAdvisorContent()
	{
		String str="---------MAIN MENU---------\n"+
				"1) Advised Student Information\n"+
				"2) Evaluate Requests\n"+
				"3) Log out\n"+
				"4) Exit\n";
		return str;
	}


	public  String createSelectedStudentsRequesPageContent (Student student)
	{			
		String str = "";
		if (student == null) {
			str = "Not selected yet"
					+ "\nPress q to quit";
			return str;
		}
		else {
			str="All course requests of "+student.getFirstName()+" "+student.getLastName()
					+" number "+student.getStudentId().getId()
					+"\n"+courseListForContent(student.getSelectedCourses())+"\n"
					+"Press 'y' to approve, 'n' to reject, 'q' to exit without taking any action.";

			return str;
		}

	}

	public  String createEvaluateRequestPageContent (ArrayList<Student> student) //take awaiting student as a parameter
	{
		String str="";
		if(student.size()==0)
		{
			str = "NO LESSON REQUESTS YET\n"
			+ "Press q to exit";

		}
		else
		{
			str = "Here is the all student that requested courses.\n";

			for(int a=0; a<student.size(); a++)
			{
				str+="\n"+(a+1)+"->"+student.get(a).getStudentId().getId()+" "+student.get(a).getFirstName()
						+" "+student.get(a).getLastName()
						+" requested "+student.get(a).getSelectedCourses().size()+ " lessons: \n";
				for(int i=0; i<student.get(a).getSelectedCourses().size(); i++)
				{
					str+= "  *"+ student.get(a).getSelectedCourses().get(i).getCourseName()
							+"\n";
				}
			}
			str+="\r\n"+ "Select one of student or press 'q' for exit.";

		}

		return str;
	}


	public   String createMyStudentsPageContent (ArrayList<Student> student)
	{
		String str="All students advised\n";
		for (int i=1; i<=student.size() ; i++ )  
		{
			str+= i+"->\n StudentID: "+
					student.get(i-1).getStudentId().getId()
					+"\n Full name: "+
					student.get(i-1).getFirstName()+" "+
					student.get(i-1).getLastName()
					+"\n Year: "+
					student.get(i-1).getTranscript().getYear()+"\n"
					+"--------------------------------------"+"\n";
		}
		str += "Press any key to back to main menu...";
		return str;
	}

	public  String createSelectableCoursesPageContent  (ArrayList<Course> courses,ArrayList<Course> coursesSelected) {
		String str="=>List of all selectable courses<=\n"+
				"-------------------------------------\n"+
				courseListForContent(courses)+"\n"+
				"=>List of all selected courses<=\n"+
				"-------------------------------------\n"+
				courseListForContent(coursesSelected)
				+"\nTo select a course, press the number of the courses offered.\n\n"	
				+"Press q to exit";


		return str;
	}

	public  String createSelectedCoursesPageContent  (ArrayList<Course> courses) {
		String str="";
		if(courses.size()==0)
		{
			str="NO COURSES SELECTED YET\n"
			+ "Press q to exit";
		}
		else {
			str="=>List of all selected courses<=\n"+
					"-------------------------------------\n"+
					courseListForContent(courses)
					+ "\nPress number to drop course \n"
					+ "Press a to send approve\n"
					+ "Press q to exit\n";


		}
		return str;

	}

	public  String createAllCoursesPageContent   (ArrayList<Course> courses) {
		String str="=>List of all courses<=\n"+
				"-------------------------------------\n"+
				courseListForContent(courses)+"\n"+
				"Press any key to back to main menu...";
		return str;
	}

	public  String createApprovedCoursesPageContent  (ArrayList<Course> courses) {
		String str="=>List of all approved courses<=\n"+
				"-------------------------------------\n"+
				courseListForContent(courses);
		return str;
	}

	public  String courseListForContent (ArrayList<Course> courses)
	{
		String str="";
		if(courses.size()==0)
		{
			str="NO COURSES SELECTED YET\n";


		}
		else {
			for (int i=1; i<=courses.size() ; i++ )
		{
			if(courses.get(i-1) instanceof CourseSession)
			{
				CourseSession session = (CourseSession) courses.get(i-1);
				str+= i+" -> "+courses.get(i-1).getCourseId().getId()+"."
						+session.getSessionId().getId()

						// session idyide alsÄ±n
						+"|"+courses.get(i-1).getCourseName()
						+"|\n"+courses.get(i-1).getLecturer().getFirstName()
						+"|\n"+courses.get(i-1).getLecturer().getLastName()
						+"|\n"+courses.get(i-1).getQuota()+"\n"
						+"--------------------------------------"+"\n";
			}
			else {
				str+= i+" -> "+courses.get(i-1).getCourseId().getId()
						+"|"+courses.get(i-1).getCourseName()
						+"|\n"+courses.get(i-1).getLecturer().getFirstName()
						+"|\n"+courses.get(i-1).getLecturer().getLastName()
						+"|\n"+courses.get(i-1).getQuota()+"\n"
						+"--------------------------------------"+"\n";
			}

		}

		}
		
		return str;
	}
}