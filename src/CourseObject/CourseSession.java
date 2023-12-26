package CourseObject;

import PersonObject.*;

import java.util.ArrayList;

public class CourseSession extends Course {

	private Id sessionId;

	public CourseSession(Id courseID, String courseName, int quota, int term, Lecturer lecturer,
						 Id sessionId, ArrayList<CourseSchedule> courseSchedules, int credit, CourseType courseType) {
		super(courseID, courseName, quota, term, lecturer, courseSchedules,credit, courseType);
		this.sessionId = sessionId;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof CourseSession){
			return ((CourseSession) o).getCourseId().getId().equals(this.getCourseId().getId()) && ((CourseSession) o).getSessionId().getId().equals(this.getSessionId().getId());
		}
		return false;
	}
	public Id getSessionId() {
		return sessionId;
	}
}