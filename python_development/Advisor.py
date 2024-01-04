from Lecturer import Lecturer
from Course import Course

class Advisor(Lecturer):
    def __init__(self, FirstName, LastName, lecturerID, password):
        super().__init__(FirstName, LastName, lecturerID, password)
        self.students = []
        self.awaitingStudents = []
        self.selectStudent = None

    def login(self, user_info, persons):
        username = user_info[0]
        password = user_info[1]
        
        for person in persons:
            if isinstance(person, Advisor):
                advisor = person
                if ("a" + str(advisor.getPersonId().getId()) == username and
                        advisor.getPassword().getPassword() == password):
                    advisor.findAwaitingStudents()
                    advisor.createSyllabus(advisor.getGivenCourses())
                    return advisor
        
        return None
    
    def findAwaitingStudents(self):
        for student in self.students:
            if student not in self.awaitingStudents and student.getRequest() == "true":
                self.awaitingStudents.append(student)

    def select_Student(self, index):
        index = index - 1
        currentStudent = self.getAwaitingStudents()[index]
        self.setSelectStudent(currentStudent)

    def approve(self):
        selectCourses = self.selectStudent.getSelectedCourses()
        for course in selectCourses:
            self.selectStudent.addApprovedCourse(course)
            course.enrollStudent(self.selectStudent)
        self.selectStudent.dropAllSelectedCourses()
        self.selectStudent.setRequest("null")
        self.removeAwaitingStudent(self.selectStudent)
        self.setSelectStudent(None)

    def disapprove(self):
        selectCourses = self.selectStudent.getSelectedCourses()
        for course in selectCourses:
            course.setQuota(course.getQuota() + 1)
            self.selectStudent.getSyllabus().removeCourseFromSyllabus(course)
        self.selectStudent.dropAllSelectedCourses()
        self.selectStudent.setRequest("false")
        self.removeAwaitingStudent(self.selectStudent)
        self.setSelectStudent(None)

    def sendNotification(self, message, type):
        defaultMessage = ""
        if not message:
            if type == "A":
                defaultMessage = "The request is approved!"
            else:
                defaultMessage = "The request is disapproved!"
        else:
            defaultMessage = message

        self.selectStudent.addUnreadNotification(defaultMessage)

    def getStudentList(self):
        return self.students

    def setStudents(self, student):
        self.students = student

    def getAwaitingStudents(self):
        return self.awaitingStudents

    def setAwaitingStudents(self, awaitingstudent):
        self.awaitingStudents = awaitingstudent

    def removeAwaitingStudent(self, student):
        ind = self.awaitingStudents.index(student)
        del self.awaitingStudents[ind]

    def getSelectStudent(self):
        return self.selectStudent

    def setSelectStudent(self, student):
        self.selectStudent = student
