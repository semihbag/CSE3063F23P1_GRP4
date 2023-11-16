public class CourseSession extends Course{

	private Id sessionId;


	public CourseSession(Id courseID, String courseName, int quota, int year, String day_hour,
						 Lecturer lecturer, Id sessionId) {
		super(courseID, courseName, quota, year, day_hour, lecturer);
		this.sessionId = sessionId;
	}

	public Id getSessionId() {
		return sessionId;
	}
	public void setSessionId(Id sessionId) {
		this.sessionId = sessionId;
	}
}