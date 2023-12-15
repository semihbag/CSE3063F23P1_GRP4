package Creator;

import java.text.DecimalFormat;
import java.util.ArrayList;

import CourseObject.Course;
import CourseObject.CourseSession;
import CourseObject.GradeClass;
import CourseObject.Syllabus;
import Page.AllCoursesPage;
import Page.ApprovedCoursesPage;
import Page.ChangePasswaordPage;
import Page.EvaluateRequestsPage;
import Page.MainMenuPageAdvisor;
import Page.MainMenuPageLecturer;
import Page.MainMenuPageStudent;
import Page.MyCoursesPage;
import Page.MyNotificationsPage;
import Page.MyStudentsPage;
import Page.Page;
import Page.ProfilePage;
import Page.SelectableCoursesPage;
import Page.SelectedCoursesPage;
import Page.SelectedMyCoursePage;
import Page.SelectedStudentRequestPage;
import Page.SyllabusPage;
import Page.TranscriptPage;

import CourseObject.*;

import PersonObject.Advisor;
import PersonObject.Lecturer;
import PersonObject.Person;
import PersonObject.Student;

public class CreatePage {
	
	private ArrayList<Page> pages = new ArrayList<>();

    //PAGE MERGE WITH SYSTEM DOMAIN
	public ArrayList<Page> createPages(Person currentUser){

		if (currentUser instanceof Student student) {
            MainMenuPageStudent mainStudent = new MainMenuPageStudent(createMainMenuPageStudentContent(student.getUnreadNotifications().size()));
			pages.add(mainStudent);

			ProfilePage profile = new ProfilePage(createProfilePageContent(student));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(createChangePasswordPage());
			pages.add(cPassword);
			
			MyNotificationsPage notifications = new MyNotificationsPage(createMyNotificationsPageContent(student.getUnreadNotifications(), student.getReadNotifications()));
			pages.add(notifications);
			
			TranscriptPage transcript = new TranscriptPage(createTranscriptPageContent(student));
			pages.add(transcript);
			
			SyllabusPage syllabus = new SyllabusPage(createSyllabusPageContent(student.getSyllabus()));
			pages.add(syllabus);

			AllCoursesPage allCourses = new AllCoursesPage(createAllCoursesPageContent(student.getCurriculum()));
			pages.add(allCourses);

			SelectableCoursesPage selectable = new SelectableCoursesPage(createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectable.setNumberOfSelectableCourses(student.getSelectableCourses().size());
			pages.add(selectable);

			SelectedCoursesPage selected = new SelectedCoursesPage(createSelectedCoursesPageContent(student.getSelectedCourses()));
			selected.setNumberOfDropableCourses(student.getSelectedCourses().size());
			pages.add(selected);

			ApprovedCoursesPage approved = new ApprovedCoursesPage(createApprovedCoursesPageContent(student.getApprovedCourses()));
			pages.add(approved);
		}
		else if (currentUser instanceof Advisor advisor) {
            MainMenuPageAdvisor mainAdvisor = new MainMenuPageAdvisor(createMainMenuPageAdvisorContent());
			pages.add(mainAdvisor);

			ProfilePage profile = new ProfilePage(createProfilePageContent(advisor));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(createChangePasswordPage());
			pages.add(cPassword);
			
			MyStudentsPage myStudents = new MyStudentsPage(createMyStudentsPageContent(advisor.getStudentList()));
			pages.add(myStudents);

			EvaluateRequestsPage evaluateRequest = new EvaluateRequestsPage(createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequest.setNumberOfRequest(advisor.getAwaitingStudents().size());
			pages.add(evaluateRequest);

			MyCoursesPage myCourses = new MyCoursesPage(createMyCoursesPageContent(advisor));
			myCourses.setNumberOfCourses(advisor.getGivenCourses().size());
			pages.add(myCourses);
			
			SelectedMyCoursePage selectedCourse = new SelectedMyCoursePage(createSelectedMyCoursePage(advisor.getSelectedCourse()));
			pages.add(selectedCourse);
			
			SelectedStudentRequestPage selectedStudentRequest = new SelectedStudentRequestPage(createSelectedStudentsRequestPageContent(advisor.getSelectStudent()));
			pages.add(selectedStudentRequest);
			
			SyllabusPage syllabus = new SyllabusPage(createSyllabusPageContent(advisor.getSyllabus()));
			pages.add(syllabus);
		}
		else if (currentUser instanceof Lecturer lecturer) {
			MainMenuPageLecturer mainLecturer = new MainMenuPageLecturer(createMainMenuPageLecturerContent());
			pages.add(mainLecturer);
			
			ProfilePage profile = new ProfilePage(createProfilePageContent(lecturer));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(createChangePasswordPage());
			pages.add(cPassword);
			
			MyCoursesPage myCourses = new MyCoursesPage(createMyCoursesPageContent(lecturer));
			myCourses.setNumberOfCourses(lecturer.getGivenCourses().size());
			pages.add(myCourses);
			
			SelectedMyCoursePage selectedCourse = new SelectedMyCoursePage(createSelectedMyCoursePage(lecturer.getSelectedCourse()));
			pages.add(selectedCourse);
		
			SyllabusPage syllabus = new SyllabusPage(createSyllabusPageContent(lecturer.getSyllabus()));
			pages.add(syllabus);
		}
		return pages;
	}

	
	
	public  String createMainMenuPageStudentContent(int numberOfUnreadNotifications) {
		String str = "";
		if (numberOfUnreadNotifications != 0) {
			str += "\u001B[32mYou have " + numberOfUnreadNotifications + " unread messages\n\u001B[0m";
		}
		str+="\u001B[1m         MAIN MENU         \n\u001B[0m"+
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

	private  String createMainMenuPageAdvisorContent() {
		String str="\u001B[1m         MAIN MENU         \u001B[0m\n"+
				"1) Profile\n" +
				"2) Advised Student Information\n"+
				"3) Evaluate Requests\n"+
				"4) Courses\n"+
				"5) My Weekly Syllabus\n"+
				"6) Log out\n"+
				"7) Exit\n";
		return str;
	}

	private String createMainMenuPageLecturerContent() {
		String str="\u001B[1m         MAIN MENU         \u001B[0m\n"+
				"1) Profile\n" +
				"2) Courses\n" +
				"3) My Weekly Syllabus\n"+
				"4) Log out\n"+
				"5) Exit\n";
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
					+"\n"+courseListForContent(student.getSelectedCourses(),0)+"\n"
					+"Approve: A\nReject: R\nQuit: Q\n";
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
				str += (a+1) + ") " + student.get(a).getStudentId().getId() + " - " +
						student.get(a).getFirstName() + " " + student.get(a).getLastName() + "\n";
			}
			str+="\r\n"+ "Select one of student.\nQuit: Q";
		}
		return str;
	}

	private   String createMyStudentsPageContent (ArrayList<Student> student) {
		String str="\u001B[1mSTUDENT ID        NAME SURNAME                  TERM\n\u001B[0m";
		for (int i=1; i<=student.size() ; i++ ) {
			String fullName = student.get(i-1).getFirstName()+ " " + student.get(i-1).getLastName();
			str += student.get(i-1).getStudentId().getId() + "         " +
					fullName + blankAfterStr(fullName, 30) + " " +
					student.get(i-1).getTranscript().getTerm()+"\n";
		}
		str += "\nPress any key to return.\n";
		return str;
	}

	public  String createSelectableCoursesPageContent  (ArrayList<Course> courses,ArrayList<Course> coursesSelected) {
		String str="";
		if(courses.size()==0) {
			str="No course to show.\n"
					+ "Q: Quit";
		}
		else {
			str=courseListForContent(courses,2)+"\n"
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
			str= courseListForContent(courses,0)
					+ "\nPress number to drop course \n"
					+ "A: Send to approval\n"
					+ "Q: Quit";
		}
		return str;
	}


	public  String createAllCoursesPageContent   (ArrayList<Course> courses) {
		String str=courseListForContent(courses,1)+"\n"+
				"\nPress any key to return.\n";
		return str;
	}


	public  String createApprovedCoursesPageContent  (ArrayList<Course> courses) {
		String str=courseListForContent(courses,0)+
				"\nPress any key to return.\n";
		return str;
	}

	private String createProfilePageContent(Person user) {
		String content = "";
		if (user instanceof Student student) {
			content += "\u001B[1m  STUDENT " + student.getStudentId().getId() + "\n\u001B[0m";
		}
		else if (user instanceof Advisor advisor) {
			content += "\u001B[1m  ADVISOR " + advisor.getLecturerId().getId() + "\n\u001B[0m";
		}
		else if (user instanceof Lecturer lecturer) {
			content += "\u001B[1m  LECTURER " + lecturer.getLecturerId().getId() + "\n\u001B[0m";
		}

		content +=  "  Name        " + user.getFirstName() + "\n" +
				"  Surname     " + user.getLastName() + "\n\n" +
				"C: Change password\n" +
				"Q: Quit\n";

		return content;
	}

	private String createChangePasswordPage() {
		return "• New password must be at least 8 character, at most 20 characters long.\n"+
				"• New password must contain an upper and a lower case letter.\n"+
				"• New password must contain at least one digit.\n"+
				"• New password must contain a special character: '.' (dot), '_'(underscore)\n";
	}

	//Information of given courses
	private String createMyCoursesPageContent(Lecturer lecturer) {
		String str = "\u001B[1m\nCOURSES\n\u001B[0m";
		for (int i = 0; i < lecturer.getGivenCourses().size(); i++) {
			Course course = lecturer.getGivenCourses().get(i);
			if (course instanceof CourseSession) {
				str += (i + 1) + ")" +blankAfterI(i+1) +
						course.getCourseId().getId() + "." + ((CourseSession) course).getSessionId().getId() +
						blankAfterStr(course.getCourseId().getId() + "." + ((CourseSession) course).getSessionId().getId(), 13);
			} else {
				str += (i + 1) + ")" + blankAfterI(i+1) +
						course.getCourseId().getId() +
						blankAfterStr(course.getCourseId().getId(), 10);

			} str += course.getCourseName() + "\n";
		}
		str+= "\nPress number to view course content.\n" +
				"Q: Quit\n";
		return str;
	}

	//Information of selected course
	public String createSelectedMyCoursePage(Course course) {
		String str = "\u001B[1m\nAll Students:\n\u001B[0m";
		if (course != null) {
			for (Student student : course.getStudentList()) {
				str += student.getStudentId().getId() + "  " +
						student.getFirstName() + blankAfterStr(student.getFirstName(), 20) +
						student.getLastName() + "\n";
			}
		}
		//Press ANY KEY to return
		str += "\nPress any key to return.\n";
		return str;
	}

	public String createSyllabusPageContent(Syllabus syllabus) {
		String str = "\u001B[1m" + "                                    TIME TABLE\n\n" + "\u001B[0m" +
				"\u001B[1m" + "                   MONDAY        TUESDAY       WEDNESDAY     " +
				"THURSDAY      FRIDAY\n" + "\u001B[0m";
		String[][] tTable = courseIds(syllabus.getSyllabus());
		for (int i = 0; i < tTable.length; i++) {
			str += "\u001B[1m" + returnHour(i) + "\u001B[0m" + "      " ;
			for (int j = 0; j < tTable[i].length; j++) {
				str += tTable[i][j] + blankAfterStr(tTable[i][j], 14) ;
			} str += "\n";
		} str += "\nPress any key to return.\n";
		return str;
	}

	private String createMyNotificationsPageContent(ArrayList<String> unreadNotifications, ArrayList<String> readNotifications) {
		String str = "\u001B[1mNOTIFICATIONS\n\u001B[0m";
		if (unreadNotifications.size() > 0) {
			str += "\u001B[1mNew\n\u001B[0m";
			for (String unreadNotification : unreadNotifications) {
				str += "\u001B[33;1m" + unreadNotification + "\n" + "\u001B[0m";
			}
		}
		if (readNotifications.size() > 0) {
			str += "\u001B[1mOld\n\u001B[0m";
			for (String readNotification : readNotifications) {
				str += readNotification + "\n";
			}
		}
		if (unreadNotifications.size() == 0 && readNotifications.size() == 0) {
			str += "\nNo notification.\n";
		}
		str += "\nPress any key to return.\n";
		return str;
	}

	private String createTranscriptPageContent(Student student) {
		String str = "\u001B[1m                               MARMARA UNIVERSITY\n\u001B[0m" +
				"\u001B[1m                                   TRANSCRIPT\n\n\u001B[0m";
		DecimalFormat dcFormat = new DecimalFormat("#.##");
		str += "Student Id : " + student.getStudentId().getId() +
				blankAfterStr("Student Id : " + student.getStudentId().getId(), 45) +
				"Faculty    : Engineering Faculty\n" +
				"Name       : " + student.getFirstName() +
				blankAfterStr("Name       : " + student.getFirstName(), 45) +
				"Department : Computer Engineering\n" +
				"Surname    : " + student.getLastName() +
				blankAfterStr("Surname    : " + student.getLastName(), 45) +
				"GPA        : " + dcFormat.format(student.getTranscript().getGPA_100())
				+"\n\n";
		ArrayList<GradeClass> allTranscriptCourses = student.getTranscript().getPassedCourses();
		allTranscriptCourses.addAll(student.getTranscript().getFailedCourses());
		for (int j = 1; j < student.getTranscript().getTerm(); j++) {
			str += "\u001B[1m" + "SEMESTER " + (j) + "\n" +"\u001B[0m";
			str += "\u001B[1mCODE         NAME                                              CREDIT | ACTS       GRADE\n\u001B[0m";
			for (GradeClass allTranscriptCourse : allTranscriptCourses) {
				if (allTranscriptCourse.getTerm() == j) {
					Course course = allTranscriptCourse.getCourse();
					str += course.getCourseId().getId() +
							blankAfterStr(course.getCourseId().getId(), 13) +
							course.getCourseName() +
							blankAfterStr(course.getCourseName(), 50) +
							course.getCredit() + " | " + course.getCredit() + "               " +
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
					courseIds[i][j] = "   -";
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


	//1: ALL
	//2: SELECTABLE
	public  String courseListForContent (ArrayList<Course> courses, int type) {
		String str="";
		if(courses.size()==0) {
			str="No course to show.\n";
		}
		else {
			if (type != 1) {
				str = "\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			}
			for (int i = 0; i < courses.size() ; i++ ) {
				if (type == 1) {
					str += allCourseLabels(i);
				}
				/* SELECTABLE FORMAT: Tam olmadı, tam yapınca açar pushlarım
				if (i == 0 && (courses.get(i).getCourseType() != CourseType.MANDATORY) && (type == 2)) {
					str += "\u001B[1m\n" + courses.get(i).getCourseType() + "\n\u001B[1m";
				}
				if ((i > 0) && (courses.get(i).getCourseType() != courses.get(i - 1).getCourseType()) && (type == 2)) {

					str += "\u001B[1m\n" + courses.get(i).getCourseType() + "\n\u001B[1m";
					str += "\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
				} */
				if(courses.get(i) instanceof CourseSession session) {
					str +=  (i + 1) + blankAfterI(i + 1) +
							courses.get(i).getCourseId().getId() + "." +session.getSessionId().getId() +
							blankAfterStr(courses.get(i).getCourseId().getId() + "." +session.getSessionId().getId(), 13);
				}
				else {
					str +=  (i + 1) + blankAfterI(i + 1) +
							courses.get(i).getCourseId().getId() +
							blankAfterStr(courses.get(i).getCourseId().getId(), 13);
				}
				str += courses.get(i).getCourseName() + blankAfterStr(courses.get(i).getCourseName(), 50) + " " +
						courses.get(i).getLecturer().getFirstName() + " "+
						courses.get(i).getLecturer().getLastName() + "\n";
			}
		}
		return str;
	}

	private String allCourseLabels(int i) {
		return switch (i) {
			case 0 -> "\u001B[1mTERM 1\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 9 -> "\u001B[1m\nTERM 2\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 17 -> "\u001B[1m\nTERM 3\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 23 -> "\u001B[1m\nTERM 4\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 28 -> "\u001B[1m\nTERM 5\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 33 -> "\u001B[1m\nTERM 6\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 39 -> "\u001B[1m\nTERM 7\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 46 -> "\u001B[1m\nTERM 8\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 50 -> "\u001B[1m\nNON-TECHNICAL ELECTIVES\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 59 -> "\u001B[1m\nTECHNICAL ELECTIVES\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			case 66 -> "\u001B[1m\nFACULTY ELECTIVES\n\u001B[0m" +
					"\u001B[1m     CODE         COURSE                                             LECTURER\n\u001B[0m";
			default -> "";
		};
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

	private String blankAfterStr(String str, int len) {
		String blank = "";
		for (int i = 0; i < len - str.length(); i++) {
			blank += " ";
		}
		return blank;
	}

	private String blankAfterI(int i) {
		if (i < 10) {
			return "    ";
		} return "   ";
	}

	
	// GETTER - SETTER

	public ArrayList<Page> getPages() {
		return pages;
	}
	
	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}
}
