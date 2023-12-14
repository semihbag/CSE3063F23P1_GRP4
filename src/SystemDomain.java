import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;

import Page.*;

public class SystemDomain {
    private LecturerCreator lecturerCreator;
    private AdvisorCreator advisorCreator;
    private CourseCreator courseCreator;
    private StudentCreator studentCreator;

    public SystemDomain() throws JSONException, IOException {
        lecturerCreator = new LecturerCreator("src\\JSON_Files\\lecturers.json");
        advisorCreator = new AdvisorCreator("src\\JSON_Files\\advisors.json",lecturerCreator.getLecturers());
        courseCreator = new CourseCreator("src\\JSON_Files\\courses.json",lecturerCreator.getLecturers());
        studentCreator = new StudentCreator("src\\JSON_Files\\Students\\","src\\JSON_Files\\student_json.txt",courseCreator.getCourses(),advisorCreator.getAdvisors());
    }


    //PAGE MERGE WITH SYSTEM DOMAIN
	public ArrayList<Page> createPages(Person currentUser){
		PageContentCreator pageContentCreator = new PageContentCreator();
		ArrayList<Page> pages = new ArrayList<>();
		if (currentUser instanceof Student student) {
            MainMenuPageStudent mainStudent = new MainMenuPageStudent(pageContentCreator.createMainMenuPageStudentContent(student.getUnreadNotifications().size()));
			pages.add(mainStudent);

			ProfilePage profile = new ProfilePage(pageContentCreator.createProfilePageContent(student));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(pageContentCreator.createChangePasswordPage());
			pages.add(cPassword);
			
			MyNotificationsPage notifications = new MyNotificationsPage(pageContentCreator.createMyNotificationsPageContent(student.getUnreadNotifications(), student.getReadNotifications()));
			pages.add(notifications);
			
			TranscriptPage transcript = new TranscriptPage(pageContentCreator.createTranscriptPageContent(student));
			pages.add(transcript);
			
			SyllabusPage syllabus = new SyllabusPage(pageContentCreator.createSyllabusPageContent(student.getSyllabus()));
			pages.add(syllabus);

			AllCoursesPage allCourses = new AllCoursesPage(pageContentCreator.createAllCoursesPageContent(student.getCurriculum()));
			pages.add(allCourses);

			SelectableCoursesPage selectable = new SelectableCoursesPage(pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
			selectable.setNumberOfSelectableCourses(student.getSelectableCourses().size());
			pages.add(selectable);

			SelectedCoursesPage selected = new SelectedCoursesPage(pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
			selected.setNumberOfDropableCourses(student.getSelectedCourses().size());
			pages.add(selected);

			ApprovedCoursesPage approved = new ApprovedCoursesPage(pageContentCreator.createApprovedCoursesPageContent(student.getApprovedCourses()));
			pages.add(approved);
		}
		else if (currentUser instanceof Advisor advisor) {
            MainMenuPageAdvisor mainAdvisor = new MainMenuPageAdvisor(pageContentCreator.createMainMenuPageAdvisorContent());
			pages.add(mainAdvisor);

			ProfilePage profile = new ProfilePage(pageContentCreator.createProfilePageContent(advisor));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(pageContentCreator.createChangePasswordPage());
			pages.add(cPassword);
			
			MyStudentsPage myStudents = new MyStudentsPage(pageContentCreator.createMyStudentsPageContent(advisor.getStudentList()));
			pages.add(myStudents);

			EvaluateRequestsPage evaluateRequest = new EvaluateRequestsPage(pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
			evaluateRequest.setNumberOfRequest(advisor.getAwaitingStudents().size());
			pages.add(evaluateRequest);

			SelectedStudentRequestPage selectedStudentRequest = new SelectedStudentRequestPage(pageContentCreator.createSelectedStudentsRequestPageContent(advisor.getSelectStudent()));
			pages.add(selectedStudentRequest);
		}
		else if (currentUser instanceof Lecturer lecturer) {
			MainMenuPageLecturer mainLecturer = new MainMenuPageLecturer(pageContentCreator.createMainMenuPageLecturerContent());
			pages.add(mainLecturer);
			
			ProfilePage profile = new ProfilePage(pageContentCreator.createProfilePageContent(lecturer));
			pages.add(profile);
			
			ChangePasswaordPage cPassword = new ChangePasswaordPage(pageContentCreator.createChangePasswordPage());
			pages.add(cPassword);
			
			MyCoursesPage myCourses = new MyCoursesPage(pageContentCreator.createMyCoursesPageContent(lecturer));
			myCourses.setNumberOfCourses(lecturer.getGivenCourses().size());
			pages.add(myCourses);
			
			SelectedMyCoursePage selectedCourse = new SelectedMyCoursePage(pageContentCreator.createSelectedMyCoursePage(lecturer.getSelectedCourse()));
			pages.add(selectedCourse);
		
			SyllabusPage syllabus = new SyllabusPage(pageContentCreator.createSyllabusPageContent(lecturer.getSyllabus()));
			pages.add(syllabus);
		}
		return pages;
	}

    public LecturerCreator getLecturerCreator() {return lecturerCreator;}

    public void setLecturerCreator(LecturerCreator lecturerCreator) { this.lecturerCreator = lecturerCreator;}

    public AdvisorCreator getAdvisorCreator() {return advisorCreator;}

    public void setAdvisorCreator(AdvisorCreator advisorCreator) {this.advisorCreator = advisorCreator;}

    public CourseCreator getCourseCreator() {return courseCreator;}

    public void setCourseCreator(CourseCreator courseCreator) {this.courseCreator = courseCreator;}

    public StudentCreator getStudentCreator() {return studentCreator;}

    public void setStudentCreator(StudentCreator studentCreator) {this.studentCreator = studentCreator;}
}