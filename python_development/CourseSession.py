from python_development.Course import Course


class CourseSession(Course):
    def __init__(self, courseID, courseName, quota, term, lecturer, sessionId, courseSchedules, credit, courseType):
        super().__init__(courseID, courseName, quota, term, lecturer, courseSchedules, credit, courseType)
        self.sessionId = sessionId

    def equals(self, obj):
        if isinstance(obj, CourseSession):
            return (
                    obj.getCourseId().getId() == self.getCourseId().getId()
                    and obj.getSessionId().getId() == self.getSessionId().getId()
            )
        return False


    def getSessionId(self):
        return self.session_id
