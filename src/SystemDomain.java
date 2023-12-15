import Creator.CreateAdvisor;
import Creator.CreateCourse;
import Creator.CreateLecturer;
import Creator.CreatePage;
import Creator.CreateStudent;
import PersonObject.*;

import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;

import Page.*;

public class SystemDomain {
    private CreateLecturer lecturerCreator;
    private CreateAdvisor advisorCreator;
    private CreateCourse courseCreator;
    private CreateStudent studentCreator;
    private CreatePage pageCreator;

    public SystemDomain() throws JSONException, IOException {
        lecturerCreator = new CreateLecturer("src\\JSON_Files\\lecturers.json");
        advisorCreator = new CreateAdvisor("src\\JSON_Files\\advisors.json",lecturerCreator.getLecturers());
        courseCreator = new CreateCourse("src\\JSON_Files\\courses.json",lecturerCreator.getLecturers());
        studentCreator = new CreateStudent("src\\JSON_Files\\Students\\","src\\JSON_Files\\student_json.txt",courseCreator.getCourses(),advisorCreator.getAdvisors());
        pageCreator = new CreatePage();
    }


    public CreateLecturer getLecturerCreator() {return lecturerCreator;}

    public void setLecturerCreator(CreateLecturer lecturerCreator) { this.lecturerCreator = lecturerCreator;}

    public CreateAdvisor getAdvisorCreator() {return advisorCreator;}

    public void setAdvisorCreator(CreateAdvisor advisorCreator) {this.advisorCreator = advisorCreator;}

    public CreateCourse getCourseCreator() {return courseCreator;}

    public void setCourseCreator(CreateCourse courseCreator) {this.courseCreator = courseCreator;}

    public CreateStudent getStudentCreator() {return studentCreator;}

    public void setStudentCreator(CreateStudent studentCreator) {this.studentCreator = studentCreator;}

	public CreatePage getPageCreator() {return pageCreator;}

	public void setPageCreator(CreatePage pageCreator) {this.pageCreator = pageCreator;}
}