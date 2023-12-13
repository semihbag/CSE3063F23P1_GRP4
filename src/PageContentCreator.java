import java.util.ArrayList;

public class PageContentCreator {

	public  String createMainMenuPageStudentContent()
	{
		String str="---------MAIN MENU---------\n"+
				"1) Profile\n" +
				"2) Notifications\n"+
				"3) Transcript\n"+
				"4) My Weekly Syllabus\n"+
				"5) All Courses\n"+
				"6) Approved Courses\n"+
				"7) Offered Courses\n"+
				"8) Selected Courses\n"+
				"9) Log out\n"+
				"10) Exit";
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
					student.getFirstName() + blankAfterStr(student.getFirstName(), 20) +
					student.getLastName() + "\n";
		}
		//Press ANY KEY to return
		str += "\nPress any key to return.\n";
		return str;
	}

///////////////////////////////////////////// burada öğrenci için bir haftalık ders programı yazdırlıcak
	public String createSyllabusPageContent(Syllabus syllabus) {
		String str = "Time Table\n\n" +
				"\t\t\t\t\tMONDAY\t\tTUESDAY\t   WEDNESDAY\t THURSDAY\t  FRIDAY\n";
		String[][] tTable = courseIds(syllabus.getSyllabus());

		for (int i = 0; i < tTable.length; i++) {
			str += returnHour(i) + "\t   ";
			for (int j = 0; j < tTable[i].length; j++) {
				str += tTable[i][j] + blankAfterStr(tTable[i][j], 14);
			} str += "\n";
		} return str;
	}

	// okunmus veya okuncak hiçmesaj yoksa da ona göre bir contetn yazarsın 
	//kg
	public String createMyNotificationsPageContent(ArrayList<String> unreadNotifications, ArrayList<String> readNotifications) {
		String str = "";
		for (String unreadNotification : unreadNotifications) {
			str += "\u001B[33;1m" + unreadNotification + "\n" + "\u001B[0m";
		}
		for (String readNotification : readNotifications) {
			str += readNotification + "\n";
		}
		if (unreadNotifications.size() == 0 && readNotifications.size() == 0) {
			str += "\nNo notification.\n";
		}
		str += "\nPress any key to return.\n";
		return str;
	}

	public String createTranscriptPageContent(Student student) {
		String str = "                               MARMARA UNIVERSITY\n" +
				     "                                   TRANSCRIPT\n\n";
		str += "Student Id : " + student.getStudentId().getId() +
				blankAfterStr("Student Id: " + student.getStudentId().getId(), 30) +
				"Faculty    : Engineering Faculty\n" +
				"Name       : " + student.getFirstName() +
				blankAfterStr("      Name: " + student.getFirstName(), 30) +
				"Department : Computer Engineering\n" +
				"Surname    : " + student.getLastName() + "\n\n";
		ArrayList<GradeClass> allTranscriptCourses = student.getTranscript().getPassedCourses();
		allTranscriptCourses.addAll(student.getTranscript().getFailedCourses());
		for (int j = 1; j < student.getTranscript().getYear(); j++) {
			str += "SEMESTER " + (j) + "\n" ;
			str += "CODE         NAME                                              CREDIT       GRADE\n";
			for (GradeClass allTranscriptCourse : allTranscriptCourses) {
				if (allTranscriptCourse.getCourse().getYear() == j) {
					Course course = allTranscriptCourse.getCourse();
					str += course.getCourseId().getId() +
							blankAfterStr(course.getCourseId().getId(), 13) +
							course.getCourseName() +
							blankAfterStr(course.getCourseName(), 50) +
							course.getCredit() + "            " +
							allTranscriptCourse.getGrade().toString() + "\n";
				}
			}
			str += "\n";
		}
		str += "Press any key to return.\n";
		return str;
	}
	
	private String[][] courseIds(Course[][] courseTable) {
		String[][] courseIds = new String[courseTable.length][courseTable[0].length];
		for (int i = 0; i < courseTable.length; i++) {
			for (int j = 0; j < courseTable[i].length; j++) {
				if (courseTable[i][j] == null) {
					courseIds[i][j] = "";
				}
				else if (courseTable[i][j] instanceof CourseSession) {
					courseIds[i][j] = courseTable[i][j].getCourseId().getId() + "." +
							((CourseSession) courseTable[i][j]).getSessionId().getId();
				} else {
					courseIds[i][j] = courseTable[i][j].getCourseId().getId();
				}
			}
		} return courseIds;
	}

	public  String courseListForContent (ArrayList<Course> courses) {
		String str="";
		if(courses.size()==0) {
			str="NO COURSE TO SHOW\n";
		}
		else {
			str = "     CODE         COURSE                                             LECTURER\n";
			for (int i=1; i<=courses.size() ; i++ ) {
				if(courses.get(i - 1) instanceof CourseSession session) {
					str +=  i + blankAfterI(i) +
						courses.get(i-1).getCourseId().getId() + "." +session.getSessionId().getId() +
						blankAfterStr(courses.get(i-1).getCourseId().getId() + "." +session.getSessionId().getId(), 13) +
						courses.get(i-1).getCourseName() + blankAfterCourseName(courses.get(i-1)) + " " +
						courses.get(i-1).getLecturer().getFirstName() + " "+
						courses.get(i-1).getLecturer().getLastName() + "\n";
				}
				else {
					str +=  i + blankAfterI(i) +
						courses.get(i-1).getCourseId().getId() +
						blankAfterStr(courses.get(i-1).getCourseId().getId(), 13) +
						courses.get(i-1).getCourseName() + blankAfterCourseName(courses.get(i-1)) + " " +
						courses.get(i-1).getLecturer().getFirstName() + " "+
						courses.get(i-1).getLecturer().getLastName() + "\n";
				}
			}
		}
		return str;
	}

	private String blankAfterStr(String str, int len) {
		String blank = "";
		for (int i = 0; i < len - str.length(); i++) {
			blank += " ";
		}
		return blank;
	}

	private String returnHour(int a) {
		return switch (a) {
			case 0 -> "08:30 - 09:20";
			case 1 -> "09:30 - 10:20";
			case 2 -> "10:30 - 11:20";
			case 3 -> "11:30 - 12:20";
			case 4 -> "13:00 - 13:50";
			case 5 -> "14:00 - 14:50";
			case 6 -> "15:00 - 15:50";
			case 7 -> "16:00 - 16:50";
			case 8 -> "17:00 - 17:50";
			case 9 -> "18:00 - 18:50";
			case 10 -> "19:00 - 19:50";
			case 11 -> "20:00 - 20:50";
			case 12 -> "21:00 - 21:50";
			default -> "";
		};
	}

	private String blankAfterCourseName(Course course) {
		String str = "";
		for (int i = 0; i < 50 - course.getCourseName().length(); i++) {
			str += " ";
		} return str;
	}

	private String blankAfterI(int i) {
		if (i < 10) {
			return "    ";
		} return "   ";
	}
}