from Course import Course


class CourseSession(Course):
    def __init__(self, course_id, course_name, quota, term, lecturer, session_id, course_schedules, credit, course_type):
        super().__init__(course_id, course_name, quota, term, lecturer, course_schedules, credit, course_type)
        self.session_id = session_id

    def is_equal(self, obj):
        if isinstance(obj, CourseSession):
            return (
                    obj.get_course_id().get_id() == self.get_course_id().get_id()
                    and obj.get_session_id().get_id() == self.get_session_id().get_id()
            )
        return False


    def get_session_id(self):
        return self.session_id
