class Course:
    def __init__(self, course_id, course_name, quota, term, lecturer, course_schedules, credit, course_type):
        self.course_id = course_id
        self.course_name = course_name
        self.quota = quota
        self.term = term
        self.lecturer = lecturer
        self.course_schedules = course_schedules
        self.credit = credit
        self.course_type = course_type
        self.prerequisite_courses = []
        self.student_list = []

    def enrollStudent(self, student):
        self.student_list.append(student)

    def equals(self, obj):
        if isinstance(obj, Course):
            return obj.course_id.get_id() == self.course_id.get_id()
        return False

    def getTerm(self):
        return self.term

    def getCourseId(self):
        return self.course_id

    def getCourseName(self):
        return self.course_name

    def getQuota(self):
        return self.quota

    def getPrerequisiteCourses(self):
        return self.prerequisite_courses

    def getStudentList(self):
        return self.student_list

    def getLecturer(self):
        return self.lecturer

    def setQuota(self, quota):
        self.quota = quota

    def setStudentList(self, student_list):
        self.student_list = student_list

    def getCourseSchedules(self):
        return self.course_schedules

    def setCourseSchedules(self, course_schedules):
        self.course_schedules = course_schedules

    def getCourseType(self):
        return self.course_type

    def setCourseType(self, course_type):
        self.course_type = course_type

    def getCredit(self):
        return self.credit

    def setCredit(self, credit):
        self.credit = credit
