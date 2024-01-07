class Transcript:
    def __init__(self, GPA_100, term, passedCourses, failedCourses):
        self.GPA_100 = GPA_100
        self.term = term
        self.totalCredit = 0
        self.passedCourses = passedCourses
        self.failedCourses = failedCourses
        self.totalCredit = self.calculateTotalCredit()

    def calculateTotalCredit(self):
        for gradeClass in self.passedCourses:
            self.totalCredit += gradeClass.getCourse().getCredit()
        return self.totalCredit

    def getGPA_100(self):
        return self.GPA_100

    def setGPA_100(self, GPA_100):
        self.GPA_100 = GPA_100

    def getTerm(self):
        return self.term

    def setTerm(self, term):
        self.term = term

    def getPassedCourses(self):
        return self.passedCourses

    def getFailedCourses(self):
        return self.failedCourses

    def getTotalCredit(self):
        return self.totalCredit

    def setTotalCredit(self, totalCredit):
        self.totalCredit = totalCredit
