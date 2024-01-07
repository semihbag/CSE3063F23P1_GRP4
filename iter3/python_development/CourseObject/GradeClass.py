class GradeClass:
    def __init__(self, course, grade):
        self.course = course
        self.grade = grade
        self.term = None

    def getCourse(self):
        return self.course

    def getGrade(self):
        return self.grade

    def setGrade(self, grade):
        self.grade = grade

    def setCourse(self, course):
        self.course = course

    def setTerm(self, term):
        self.term = term

    def getTerm(self):
        return self.term
