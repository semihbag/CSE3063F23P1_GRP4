class CourseSchedule:
    def __init__(self, course_day, course_hours):
        self.course_day = course_day
        self.course_hours = course_hours

    def get_course_day(self):
        return self.course_day

    def set_course_day(self, course_day):
        self.course_day = course_day

    def get_course_hours(self):
        return self.course_hours

    def set_course_hours(self, course_hours):
        self.course_hours = course_hours