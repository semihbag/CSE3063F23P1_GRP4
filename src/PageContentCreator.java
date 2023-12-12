import java.util.ArrayList;

public class PageContentCreator {

	public  String crateMainMenuPageStudentContent()
	{
		String str="---------MAIN MENU---------\n"+
				"1) All Courses\n"+
				"2) Approved Courses\n"+
				"3) Offered Courses\n"+
				"4) Selected Courses\n"+
				"5) Log out\n"+
				"6) Exit";
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


	public  String createSelectedStudentsRequestPageContent(Student student) {
		String str = "";
		if (student == null) {
			str = "No course has been selected yet.\n"
					+ "Quit: Q";
		}
		else {
			str="\nRequests of " + student.getFirstName() + " " + student.getLastName()
					+ " " + student.getStudentId().getId()
					+"\n"+courseListForContent(student.getSelectedCourses())+"\n"
					+"Approve: Y\nReject: R\nQuit: Q\n";
		}
		return str;
	}

	public  String createEvaluateRequestPageContent (ArrayList<Student> student) {
		String str="";
		if (student.size()==0) {
			str = "No request to show.\n"
			+ "Quit: Q";
		}
		else {
			for(int a=0; a<student.size(); a++) {
				str += (a+1) + ")  " + student.get(a).getStudentId().getId() + " - " +
						student.get(a).getFirstName() + " " + student.get(a).getLastName() + "\n";
			}
			str+="\r\n"+ "Select one of student.\nQuit: Q";
		}
		return str;
	}

	public   String createMyStudentsPageContent (ArrayList<Student> student) {
		String str="STUDENT ID        NAME SURNAME                  YEAR\n";
		for (int i=1; i<=student.size() ; i++ ) {
			String fullName = student.get(i-1).getFirstName()+ " " + student.get(i-1).getLastName();
			str += student.get(i-1).getStudentId().getId() + "         " +
					fullName + blankFullName(fullName) + " " +
					student.get(i-1).getTranscript().getYear()+"\n";

		}
		str += "Press any key to back to main menu.";
		return str;
	}

	public String blankFullName(String name) {
		String str = "";
		for (int i = 0; i < 30 - name.length(); i++) {
			str+=" ";
		}
		return str;
	}


	public  String createSelectableCoursesPageContent  (ArrayList<Course> courses,ArrayList<Course> coursesSelected) {
		String str="";
		if(courses.size()==0) {
			str="NO COURSE TO SHOW\n"
					+ "Press q to exit";
		}
		else {
			str=courseListForContent(courses)+"\n"
				+"\nTo select a course, press the number of the courses offered.\n\n"
				+"Press q to exit";
		}
		return str;
	}

	public  String createSelectedCoursesPageContent  (ArrayList<Course> courses) {
		String str="";
		if(courses.size()==0) {
			str="No course to show.\n"
			+ "Press q to exit";
		}
		else {
			str= courseListForContent(courses)
					+ "\nPress number to drop course \n"
					+ "Press a to send approve\n"
					+ "Press q to exit\n";
		}
		return str;
	}

	public  String createAllCoursesPageContent   (ArrayList<Course> courses) {
		String str=courseListForContent(courses)+"\n"+
				"Press any key to back to main menu...";
		return str;
	}

	public  String createApprovedCoursesPageContent  (ArrayList<Course> courses) {
		String str=courseListForContent(courses)+
				"Press any key to back to main menu...";
		return str;
	}

	public String createProfilePageStudentContent (Student student) {
		return "buralar hep dutluktu";
	}

	public String createProfilePageAdvisorContent (Advisor adviosr) {
		return "buralar hep dutluktu";
	}
	
	public String createProfilePageStudentContent (Lecturer lecturer) {
		return "buralar hep dutluktu";
	}
	
	public String createChangePasswordPage() {
		return null;
	}
	
	public  String courseListForContent (ArrayList<Course> courses) {
		String str="";
		if(courses.size()==0) {
			str="NO COURSE TO SHOW\n";
		}
		else {
			str = "     CODE         COURSE                                   LECTURER\n";
			for (int i=1; i<=courses.size() ; i++ ) {
				if(courses.get(i - 1) instanceof CourseSession session) {
					str +=  i + blankAfterI(i) +
						courses.get(i-1).getCourseId().getId() + "." +session.getSessionId().getId() + "    " +
						courses.get(i-1).getCourseName() + blankAfterCourseName(courses.get(i-1)) + " " +
						courses.get(i-1).getLecturer().getFirstName() + " "+
						courses.get(i-1).getLecturer().getLastName() + "\n";
				}
				else {
					str +=  i + blankAfterI(i) +
						courses.get(i-1).getCourseId().getId() + "      " +
						courses.get(i-1).getCourseName() + blankAfterCourseName(courses.get(i-1)) + " " +
						courses.get(i-1).getLecturer().getFirstName() + " "+
						courses.get(i-1).getLecturer().getLastName() + "\n";
				}
			}
		}
		return str;
	}

	public String blankAfterCourseName(Course course) {
		String str = "";
		for (int i = 0; i < 40 - course.getCourseName().length(); i++) {
			str += " ";
		} return str;
	}

	public String blankAfterI(int i) {
		if (i < 10) {
			return "    ";
		} return "   ";
	}
}