class GradeClass:
    def __init__(self, course, grade):
        self.course = course
        self.grade = grade
        self.term = None

    def get_course(self):
        return self.course

    def get_grade(self):
        return self.grade

    def set_grade(self, grade):
        self.grade = grade

    def set_course(self, course):
        self.course = course

    def set_term(self, term):
        self.term = term

    def get_term(self):
        return self.term
