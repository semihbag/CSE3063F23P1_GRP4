package CourseObject;

import PersonObject.*;

import java.util.ArrayList;

public class Course {
	private Id courseId;
	private String courseName;
	private int quota, term;
	private CourseType courseType;
	private ArrayList<Course> prerequisiteCourses=new ArrayList<>() ;
	private ArrayList<Student> studentList=new ArrayList<>();
	private ArrayList<CourseSchedule> courseSchedules;
	private Lecturer lecturer;
	private int credit;


	public Course(Id courseId, String courseName, int quota, int term,
				  Lecturer lecturer, ArrayList<CourseSchedule> courseSchedules, int credit, CourseType courseType) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.quota = quota;
		this.term = term;
		this.lecturer = lecturer;
		this.courseSchedules = courseSchedules;
		this.credit = credit;
		this.courseType = courseType;
	}

	public void  enrollStudent (Student student) {
		studentList.add(student);
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Course){
            return ((Course) o).courseId.getId().equals(this.getCourseId().getId());
		}
		return false;
	}

	public int getTerm() {
		return term;
	}

	public Id getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getQuota() {
		return quota;
	}

	

	public ArrayList<Course> getPrerequisiteCourses() {
		return prerequisiteCourses;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	public ArrayList<CourseSchedule> getCourseSchedules() { //
		return courseSchedules;
	}


	public void setCourseSchedules(ArrayList<CourseSchedule> courseSchedules) { //
		this.courseSchedules = courseSchedules;
	}
	public CourseType getCourseType() {
		return this.courseType;
	}

	public void setCourseType(CourseType type) {
		this.courseType = type;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
}
