class Transcript:
    def __init__(self, gpa_100, term, passed_courses, failed_courses):
        self.gpa_100 = gpa_100
        self.term = term
        self.total_credit = 0
        self.passed_courses = passed_courses
        self.failed_courses = failed_courses
        self.total_credit = self.calculate_total_credit()

    def calculate_total_credit(self):
        for grade_class in self.passed_courses:
            self.total_credit += grade_class.get_course().get_credit()
        return self.total_credit

    def get_gpa_100(self):
        return self.gpa_100

    def set_gpa_100(self, gpa_100):
        self.gpa_100 = gpa_100

    def get_term(self):
        return self.term

    def set_term(self, term):
        self.term = term

    def get_passed_courses(self):
        return self.passed_courses

    def get_failed_courses(self):
        return self.failed_courses

    def get_total_credit(self):
        return self.total_credit

    def set_total_credit(self, total_credit):
        self.total_credit = total_credit
