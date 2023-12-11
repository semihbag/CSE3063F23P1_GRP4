import java.util.ArrayList;

public class CourseSession extends Course{

	private Id sessionId;

	public CourseSession(Id courseID, String courseName, int quota, int year,
						 Lecturer lecturer, Id sessionId,ArrayList<CourseSchedule> courseSchedules) {
		super(courseID, courseName, quota, year, lecturer, courseSchedules); 
		this.sessionId = sessionId;
	}

	public Id getSessionId() {
		return sessionId;
	}
}