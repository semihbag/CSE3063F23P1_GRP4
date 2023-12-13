import java.util.ArrayList;

public class PageContentCreator {

	public  String createMainMenuPageStudentContent()
	{
		String str="---------MAIN MENU---------\n"+
				"1) Profile\n" +
				"2) Notificctions\n"+
				"3) My Weekly Syllabus\n"+
				"4) All Courses\n"+
				"5) Approved Courses\n"+
				"6) Offered Courses\n"+
				"7) Selected Courses\n"+
				"8) Log out\n"+
				"9) Exit";
		return str;
	}

	public  String createMainMenuPageAdvisorContent()
	{
		String str="---------MAIN MENU---------\n"+
				"1) Profile\n" +
				"2) Advised Student Information\n"+
				"3) Evaluate Requests\n"+
				"4) Log out\n"+
				"5) Exit\n";
		return str;
	}

	public String createMainMenuPageLecturerContent() {
		String str="---------MAIN MENU---------\n"+
				"1) Profile\n" +
				"2) Courses\n"+
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
				"Press any key to back to main menu.";
		return str;
	}

	public  String createApprovedCoursesPageContent  (ArrayList<Course> courses) {
		String str=courseListForContent(courses)+
				"Press any key to back to main menu.";
		return str;
	}

	public String createProfilePageStudentContent(Student student) {
		return "--STUDENT " + student.getStudentId().getId() + "--\n" +
				"  Name        " + student.getFirstName() + "\n" +
				"  Surname     " + student.getLastName() + "\n\n" +
				"C: Change password\n" +
				"Q: Quit\n";
	}

	public String createProfilePageAdvisorContent (Advisor adviosr) {
		return "--ADVISOR " + adviosr.getLecturerId().getId() + "--\n" +
				"  Name        " + adviosr.getFirstName() + "\n" +
				"  Surname     " + adviosr.getLastName() + "\n\n" +
				"C: Change password\n" +
				"Q: Quit\n";
	}
	
	public String createProfilePageLecturerContent(Lecturer lecturer) {
		return "--LECTURER " + lecturer.getLecturerId().getId() + "--\n" +
				"  Name        " + lecturer.getFirstName() + "\n" +
				"  Surname     " + lecturer.getLastName() + "\n\n" +
				"C: Change password\n" +
				"Q: Quit\n";
	}
	
///////////////////////////////////////////////
	public String createChangePasswordPage() {
		return "bu şu anlık durakoysın";
	}
	
///////////////////////////////////////////////	 burda verilen derslerin bilgileri olcak
	public String createMyCoursesPageContent(Lecturer lecturer) {
		return "duru burası sende";
	}
	
///////////////////////////////////////////////	 burda seçilen  derslerin bilgileri olcak öğrencileri falan görebilicez
// çıkmak için herhangi bir tuşa basması yeterli olacak allcourses mantıgı gibi
	public String createSelectedMyCoursePage(Course course) {
		return "duru burası sende";
	}

///////////////////////////////////////////// burada öğrenci için bir haftalık ders programı yazdırlıcak
	public String createSyllabusPageStudentContent(Syllabus syllabus) {
		return "duru burası sende";
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

	private String blankAfterCourseName(Course course) {
		String str = "";
		for (int i = 0; i < 40 - course.getCourseName().length(); i++) {
			str += " ";
		} return str;
	}

	private String blankAfterI(int i) {
		if (i < 10) {
			return "    ";
		} return "   ";
	}
}