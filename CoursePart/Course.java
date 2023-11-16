package CoursePart;


import java.util.ArrayList;

public class Course {
	private Id courseID;
	private String courseName;
	private int quota;
	private int year;
	private String day_hour;
    private ArrayList<Course> prerequisiteCourses=new ArrayList<>() ;
    private ArrayList<Student> studentList=new ArrayList<>();
    private Lecturer lecturer;
    
	
	public Course(Id courseID, String courseName, int quota, int year, String day_hour,
			 Lecturer lecturer) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.quota = quota;
		this.year=year;
		this.day_hour = day_hour;	
		this.lecturer = lecturer;
	}
	
    public void  enrollStudent ( Student student )
    {	
		studentList.add(student);
    	
    }
  
	public Id getCourseID() {
		return courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	
	public int getQuota() {
		return quota;
	}

	public String getDay_hour() {
		return day_hour;
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

	public void setSemester(int semester) {
		this.year = semester;
	}

	public void setCourseID(Id courseID) {
		this.courseID = courseID;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public void setDay_hour(String day_hour) {
		this.day_hour = day_hour;
	}

	public void setPrerequisiteCourses(ArrayList<Course> prerequisiteCourses) {
		this.prerequisiteCourses = prerequisiteCourses;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
}

