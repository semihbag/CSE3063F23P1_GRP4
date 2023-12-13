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
		str += "\nPress any key to return.\n";
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
					+ "Q: Quit";
		}
		else {
			str=courseListForContent(courses)+"\n"
				+"\nTo select a course, press the number of the courses offered.\n\n"
					+ "Q: Quit";
		}
		return str;
	}

	public  String createSelectedCoursesPageContent  (ArrayList<Course> courses) {
		String str="";
		if(courses.size()==0) {
			str="No course to show.\n"
					+ "Q: Quit";
		}
		else {
			str= courseListForContent(courses)
					+ "\nPress number to drop course \n"
					+ "A: Send to approval\n"
					+ "Q: Quit";
		}
		return str;
	}

	public  String createAllCoursesPageContent   (ArrayList<Course> courses) {
		String str=courseListForContent(courses)+"\n"+
				"\nPress any key to return.\n";
		return str;
	}

	public  String createApprovedCoursesPageContent  (ArrayList<Course> courses) {
		String str=courseListForContent(courses)+
				"\nPress any key to return.\n";
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
	
	public String createChangePasswordPage() {

		return "bu şu anlık durakoysın";
	}

	//Information of given courses
	public String createMyCoursesPageContent(Lecturer lecturer) {
		String str = "\nMy Courses\n";
		for (Course course : lecturer.getGivenCourses()) {
			str += course.getCourseId().getId() + " - " +
					course.getCourseName() + "\n";
		}
		str+= "\nPress number to view course content.\n" +
				"Q: Quit\n";
		return str;
	}

	//Information of selected course
	public String createSelectedMyCoursePage(Course course) {
		String str = "\nAll Students:\n";
		for (Student student : course.getStudentList()) {
			str += student.getStudentId().getId() + "  " +
					student.getFirstName() + blankAfterName(student.getFirstName()) +
					student.getLastName() + "\n";
		}
		//Press ANY KEY to return
		str += "\nPress any key to return.\n";
		return str;
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

	private String blankAfterName(String str) {
		String a = "";
		for (int i = 0; i < 20 - str.length(); i++) {
			a += " ";
		}
		return a;
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