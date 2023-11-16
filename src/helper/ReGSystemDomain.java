package helper;

import java.util.ArrayList;

import pagePackage.*;

public class ReGSystemDomain {
	
	public ArrayList<Page> createPages(Person currentUser){
//		PageContentCreator pageContentCreator = new PageContentCreator();
		ArrayList<Page> pages = new ArrayList();
		
		if (currentUser instanceof Student) {
			Student student = (Student) currentUser;
			
//			MainMenuPageStudent mainStudent = new MainMenuPageStudent(pageContentCreator.crateMainMenuPageStudentContent());
//			pages.add(mainStudent);
			
//			AllCoursesPage allCourses = new AllCoursesPage(pageContentCreator.createAllCoursesPageContent(student.getCurriculum()));
//			pages.add(allCourses);
			
//			SelectableCoursesPage selectable = new SelectableCoursesPage(pageContentCreator.createSelectableCoursesPageContent(student.getSelectableCourses(), student.getSelectedCourses()));
//			selectable.setNumberOfSelectableCourses(student.getSelectableCourses().size());
//			pages.add(selectable);

//			SelectedCoursesPage selected = new SelectedCoursesPage(pageContentCreator.createSelectedCoursesPageContent(student.getSelectedCourses()));
//			selected.setNumberOfDropableCourses(student.getSelectedCourses().size());
//			pages.add(selected);

//			ApprovedCoursesPage approved = new ApprovedCoursesPage(pageContentCreator.createApprovedCoursesPageContent(student.getApprovedCourses()));
//			pages.add(approved);

		}
		
		if (currentUser instanceof Advisor) {
			Advisor advisor = (Advisor) currentUser;
			
//			MainMenuPageAdvisor mainAdvisor = new MainMenuPageAdvisor(pageContentCreator.createMainMenuPageAdvisorContent());
//			pages.add(mainAdvisor);

//			MyStudentsPage myStudents = new MyStudentsPage(pageContentCreator.createMyStudentsPageContent(advisor.getStudentList()));
//			pages.add(myStudents);

//			EvaluateRequestsPage evaluateRequest = new EvaluateRequestsPage(pageContentCreator.createEvaluateRequestPageContent(advisor.getAwaitingStudents()));
//			evaluateRequest.setNumberOfRequest(advisor.getAwaitingStudents().size());
//			pages.add(evaluateRequest);

//			SelectedStudentRequestPage selectedStudentRequest = new SelectedStudentRequestPage(pageContentCreator.createSelectedStudentsRequesPageContent(advisor.getSelectStudent()));
//			pages.add(selectedStudentRequest);

			
		}
		
		return pages;
	}
}
