from CourseSession import CourseSession
from CourseType import CourseType
from Person import Person
from Mark import Mark

class Student(Person):
    def __init__(self, firstName, lastName, personId, password, advisor, transcript, curriculum):
        super().__init__(firstName, lastName, personId,password)
        self.advisor = advisor
        self.transcript = transcript
        self.selectedCourseCredit = 0
        self.marks = []
        self.unreadNotifications = []
        self.readNotifications = []
        self.selectableCourses = []
        self.selectedCourses = []
        self.approvedCourses = []
        self.curriculum = curriculum
        self.request = None

    def filterCourses(self):
        if len(self.approvedCourses) != 0:
            self.createSyllabus(self.approvedCourses)
        else:
            self.createSyllabus(self.selectedCourses)

        for course in self.curriculum:
            if not self.isSelectedCourse(course) and not self.isPassedCourse(course) and \
                    self.isPrerequisiteCoursesPassed(course) and self.isUnderQuota(course) and \
                    (self.checkCourseType(course) or self.isFailedCourse(course)):
                self.selectableCourses.append(course)

        self.setMarksInitial()

    def login(self, userInfo, persons):
        username, password = userInfo
        for person in persons:
            if f"o{person.personId}" == username and person.password.password == password:
                return person
        return None

    def isSelectedCourse(self, course):
        for selectedCourse in self.selectedCourses:
            if isinstance(course, CourseSession):
                if isinstance(selectedCourse, CourseSession):
                    if selectedCourse.getSessionId().getId() == course.getSessionId().getId():
                        return True
                else:
                    if selectedCourse.getCourseId().getId() == course.getCourseId().getId():
                        return True
            else:
                if selectedCourse.getCourseId().getId() == course.getCourseId().getId():
                    return True
        return False

    def isPassedCourse(self, course):
        for passedCourse in self.transcript.getPassedCourses():
            if passedCourse.getCourse().getCourseId().getId() == course.getCourseId().getId():
                return True
        return False

    def isPrerequisiteCoursesPassed(self, course):
        for precourse in course.getPrerequisiteCourses():
            if not self.isPassedCourse(precourse):
                return False
        return True

    def isUnderQuota(self, course):
        return course.quota > 0

    def isFailedCourse(self, course):
        for failedCourse in self.transcript.getFailedCourses():
            if failedCourse.getCourse().getCourseId().getId() == course.getCourseId().getId() and self.transcript.getTerm() % 2 == course.getTerm() % 2:
                return True
        return False

    def addSelectableCourse(self, course):
        self.selectableCourses.append(course)

    def addApprovedCourse(self, course):
        self.approvedCourses.append(course)

    def dropAllSelectedCourses(self):
        self.selectedCourses.clear()
        self.selectedCourseCredit = 0
        print("1------------------------------------------0", len(self.selectedCourses))
        self.setMarks()  # Assuming setMarks() is a function/method available in your code

    def sendToApproval(self):
        self.request = "true"

    def addSelectedCourse(self, i):
        course = self.selectableCourses[i - 1]
        mark = self.finalCheckSelectedCourse(course)

        if mark == Mark.SUCCESSFUL:
            self.selectedCourses.append(course)
            self.selectedCourseCredit += course.credit
            course.setQuota(course.getQuota() - 1)
            self.syllabus.addCourseToSyllabus(course)
            self.setMarks()
            return True

        if mark == Mark.ERROR_ALREADY_SENDED:
            print("You have already sent a request to your advisor!")

        if mark == Mark.ERROR_CREDIT_LIMIT:
            print("You exceed the selectable course credit limit!!")

        if mark == Mark.ERROR_CONFLICT:
            print(
                f"The course you want to add {course.courseName} conflicts with {self.syllabus.findConflictedCourseName(course)}!")

        if mark == Mark.ERROR_SAME_TYPE:
            print(f"You cannot select {course.courseType} course more than once in one term!")

        if mark == Mark.SELECTED:
            if isinstance(course, CourseSession):
                print(f"You have already selected a session from {course.Course.getCourseName()}.")
            else:
                print(f"You have already selected {course.courseName}.")

        return False

    def setMarks(self):
        self.marks.clear()
        for tempCourse in self.selectableCourses:
            if self.isSelectedCourse(tempCourse):
                self.marks.append(Mark.SELECTED)
                continue
            self.marks.append(self.finalCheckSelectedCourse(tempCourse))

    def setMarksInitial(self):
        for _ in self.selectableCourses:
            self.marks.append(Mark.SUCCESSFUL)

    def finalCheckSelectedCourse(self, course):
        if self.request == "false":
            if not self.isSelectedCourse(course):
                if self.selectedCourseCredit + course.credit < 40:
                    courseType = course.courseType
                    if courseType != CourseType.CourseType.MANDATORY:
                        if self.exceedTerm(courseType):
                            return Mark.ERROR_SAME_TYPE
                    if not self.syllabus.checkConflict(course):
                        return Mark.SUCCESSFUL
                    else:
                        return Mark.ERROR_CONFLICT
                return Mark.ERROR_CREDIT_LIMIT
            return Mark.SELECTED
        return Mark.ERROR_ALREADY_SENDED

    def checkCourseType(self, course):
        courseType = course.courseType
        if courseType == CourseType.NONTECHNICAL and self.transcript.term >= 2:
            if not self.exceed(courseType, 2):
                return True
        elif courseType == CourseType.TECHNICAL:
            if self.transcript.term == 7 or self.transcript.term == 8:
                return True
        elif courseType == CourseType.FACULTY:
            if (self.transcript.term == 7 or self.transcript.term == 8) and not self.exceed(courseType, 1):
                return True
        elif courseType == CourseType.MANDATORY:
            if course.courseName == "Engineering Project I" or course.courseName == "Engineering Project II":
                if self.transcript.totalCredit >= 165:
                    return True
                return False
            elif course.term == self.transcript.term:
                return True
            elif (self.transcript.GPA_100 >= 3.0 and self.transcript.term >= 3) and (
                    (
                            course.courseName == "Is Sagligi ve Guvenligi I" or course.courseName == "Is Sagligi ve Guvenligi II") or
                    (course.term == self.transcript.term + 2)):
                return True
        return False

    def exceed(self, courseType, limit):
        ct = sum(1 for gradeClass in self.transcript.passedCourses if gradeClass.course.courseType == courseType)
        return ct == limit

    def exceedTerm(self, courseType):
        ct = sum(1 for selectedCourse in self.selectedCourses if selectedCourse.courseType == courseType)
        return ct > 0

    def clearUnreadNotification(self):
        self.readNotifications.extend(self.unreadNotifications)
        self.unreadNotifications.clear()

    def addUnreadNotification(self, notification):
        self.unreadNotifications.append(notification)

    def dropCourse(self, i):
        course = self.selectedCourses.pop(i - 1)
        self.selectedCourseCredit -= course.credit
        self.syllabus.removeCourseFromSyllabus(course)
        self.setMarks()

    def addAllSessions(self, course):
        for curriculumCourse in self.curriculum:
            if course.courseId == curriculumCourse.courseId:
                if curriculumCourse not in self.selectableCourses:
                    self.selectableCourses.append(curriculumCourse)

    def removeAllSessions(self, course, selectableCourses):
        i = 0
        while i < len(selectableCourses):
            if course.getCourseId().getId() == selectableCourses[i].getCourseId().getId():
                selectableCourses.pop(i)
                i -= 1
            i += 1

    def createSyllabus(self, courses):
        self.syllabus.fillSyllabus(courses)
    
    def getSyllabus(self):
        return self.syllabus
    
    def setSyllabus(self, syllabus):
        self.syllabus = syllabus

    def getCurriculum(self):
        return self.curriculum

    def getAdvisor(self):
        return self.advisor


    def setAdvisor(self, advisor):
        self.advisor = advisor


    def getTranscript(self):
        return self.transcript


    def setTranscript(self, transcript):
        self.transcript = transcript


    def getSelectableCourses(self):
        return self.selectableCourses


    def getSelectedCourses(self):
        return self.selectedCourses


    def setSelectedCourses(self, selectedCourses):
        self.selectedCourses = selectedCourses

    def getApprovedCourses(self):
        return self.approvedCourses


    def setApprovedCourses(self, approvedCourses):
        self.approvedCourses = approvedCourses


    def getRequest(self):
        return self.request


    def setRequest(self, request):
        self.request = request


    def getSelectedCourseCredit(self):
        return self.selectedCourseCredit


    def setSelectedCourseCredit(self, selectedCourseCredit):
        self.selectedCourseCredit = selectedCourseCredit


    def getUnreadNotifications(self):
        return self.unreadNotifications

    def setUnreadNotifications(self, unreadNotifications):
        self.unreadNotifications = unreadNotifications


    def getReadNotifications(self):
        return self.readNotifications


    def setReadNotifications(self, readNotifications):
        self.readNotifications = readNotifications


    def getMarks(self):
        return self.marks


    def setSelectableCourses(self, value):
        self._selectableCourses = value


    def setCurriculum(self, value):
        self._curriculum = value
