from Lecturer import Lecturer

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
                if ("a" + str(advisor.get_person_id().get_id()) == username and
                        advisor.get_password().get_password() == password):
                    advisor.find_awaiting_students()
                    advisor.create_syllabus(advisor.get_given_courses())
                    return advisor
        
        return None
    
    def findAwaitingStudents(self):
        for student in self.students:
            if student not in self.awaitingStudents and student.getRequest() == "true":
                self.awaitingStudents.append(student)

    def select_student(self, index):
        index = index - 1
        currentStudent = self.getAwaitingStudents()[index]
        self.setSelectStudent(currentStudent)

    def approve(self):
        selectCourses = self.selectStudent.get_selected_courses()
        for course in selectCourses:
            self.selectStudent.add_approved_course(course)
            course.enroll_student(self.selectStudent)
        self.selectStudent.drop_all_selected_courses()
        self.selectStudent.set_request("null")
        self.removeAwaitingStudent(self.selectStudent)
        self.setSelectStudent(None)

    def disapprove(self):
        selectCourses = self.selectStudent.get_selected_courses()
        for course in selectCourses:
            course.set_quota(course.get_quota() + 1)
            self.selectStudent.get_syllabus().remove_course_from_syllabus(course)
        self.selectStudent.drop_all_selected_courses()
        self.selectStudent.set_request("false")
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

        self.selectStudent.add_unread_notification(defaultMessage)

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
