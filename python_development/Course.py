class Course:
    def __init__(self, courseId, courseName, quota, term, lecturer, courseSchedules, credit, courseType):
        self.courseId = courseId
        self.courseName = courseName
        self.quota = quota
        self.term = term
        self.lecturer = lecturer
        self.courseSchedules = courseSchedules
        self.credit = credit
        self.courseType = courseType
        self.prerequisiteCourses = []
        self.studentList = []

    def enrollStudent(self, student):
        self.studentList.append(student)

    def equals(self, obj):
        if isinstance(obj, Course):
            return obj.getCourseId().get_id() == self.getCourseId().get_id()
        return False

    def getTerm(self):
        return self.term

    def getCourseId(self):
        return self.courseId

    def getCourseName(self):
        return self.courseName

    def getQuota(self):
        return self.quota

    def getPrerequisiteCourses(self):
        return self.prerequisiteCourses

    def getStudentList(self):
        return self.studentList

    def getLecturer(self):
        return self.lecturer

    def setQuota(self, quota):
        self.quota = quota

    def setStudentList(self, studentList):
        self.studentList = studentList

    def getCourseSchedules(self):
        return self.courseSchedules

    def setCourseSchedules(self, courseSchedules):
        self.courseSchedules = courseSchedules

    def getCourseType(self):
        return self.courseType

    def setCourseType(self, courseType):
        self.courseType = courseType

    def getCredit(self):
        return self.credit

    def setCredit(self, credit):
        self.credit = credit
