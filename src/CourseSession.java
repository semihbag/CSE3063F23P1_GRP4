import java.util.ArrayList;

public class CourseSession extends Course{

	private Id sessionId;

	public CourseSession(Id courseID, String courseName, int quota, int term, Lecturer lecturer,
						 Id sessionId,ArrayList<CourseSchedule> courseSchedules,int credit, CourseType courseType) {
		super(courseID, courseName, quota, term, lecturer, courseSchedules,credit, courseType);
		this.sessionId = sessionId;
	}

	public Id getSessionId() {
		return sessionId;
	}
}