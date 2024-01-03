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

    def enroll_student(self, student):
        self.student_list.append(student)

    def is_equal(self, obj):
        if isinstance(obj, Course):
            return obj.course_id.get_id() == self.course_id.get_id()
        return False

    def get_term(self):
        return self.term

    def get_course_id(self):
        return self.course_id

    def get_course_name(self):
        return self.course_name

    def get_quota(self):
        return self.quota

    def get_prerequisite_courses(self):
        return self.prerequisite_courses

    def get_student_list(self):
        return self.student_list

    def get_lecturer(self):
        return self.lecturer

    def set_quota(self, quota):
        self.quota = quota

    def set_student_list(self, student_list):
        self.student_list = student_list

    def get_course_schedules(self):
        return self.course_schedules

    def set_course_schedules(self, course_schedules):
        self.course_schedules = course_schedules

    def get_course_type(self):
        return self.course_type

    def set_course_type(self, course_type):
        self.course_type = course_type

    def get_credit(self):
        return self.credit

    def set_credit(self, credit):
        self.credit = credit
