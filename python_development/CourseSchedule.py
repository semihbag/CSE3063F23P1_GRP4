class CourseSchedule:
    def __init__(self, course_day, course_hours):
        self.course_day = course_day
        self.course_hours = course_hours

    def getCourseDay(self):
        return self.course_day

    def setCourseDay(self, course_day):
        self.course_day = course_day

    def getCourseHours(self):
        return self.course_hours

    def setCourseHours(self, course_hours):
        self.course_hours = course_hours