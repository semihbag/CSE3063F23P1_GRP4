import java.util.ArrayList;

public class Course {
	private Id courseId;
	private String courseName, day_hour;
	private int quota, year;
    private ArrayList<Course> prerequisiteCourses=new ArrayList<>() ;
    private ArrayList<Student> studentList=new ArrayList<>();
    private Lecturer lecturer;
    
	
	public Course(Id courseId, String courseName, int quota, int year, String day_hour,
				  Lecturer lecturer) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.quota = quota;
		this.year = year;
		this.day_hour = day_hour;
		this.lecturer = lecturer;
}
	
    public void  enrollStudent (Student student) {
		studentList.add(student);
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
    
	public int getYear() {
		return year;
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

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
}