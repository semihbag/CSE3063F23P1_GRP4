import json

from python_development.Advisor import Advisor
from python_development.CourseSession import CourseSession
from python_development.Lecturer import Lecturer
from python_development.Page.LoginPage import LoginPage
from python_development.Page.PageType import PageType
from python_development.Student import Student
from python_development.System.SystemDomain import SystemDomain
from python_development.Page import PageType
import time


class SystemClass:
    def __init__(self, user_interface):
        self.domain = SystemDomain()
        self.currentUser = None
        self.userInterface = user_interface

        login = LoginPage("Welcome! Please enter your username/password.")
        self.userInterface.addPage(login)
        self.userInterface.setCurrentPage("LOGIN_PAGE")

    def run(self):
        while True:
            self.userInterface.display()
            self.listenUserInterface(self.userInterface.getSystemMessage())

    def login(self, user_info):
        all_users = None
        if user_info[0][0] == 'o':
            all_users = self.domain.getStudentCreator().getStudents()
            self.currentUser = Student.login(Student(None, None, None, None,
                                                     None, None, None), user_info, all_users)
        elif user_info[0][0] == 'a':
            all_users = self.domain.getAdvisorCreator().getAdvisors()
            self.currentUser = Advisor.login(Advisor(None, None, None, None),
                                             user_info, all_users)
        elif user_info[0][0] == 'l':
            all_users = self.extract_advisors()
            self.currentUser = Lecturer.login(Lecturer(None, None, None, None),
                                              user_info, all_users)
        if self.currentUser is None:
            print("Username/Password incorrect.\n")
        else:
            self.userInterface.setPages(self.domain.getPageCreator().create_pages(self.currentUser))
            self.userInterface.setCurrentPage("MAIN_MENU_PAGE")
            print("LOGIN SUCCESSFUL - WELCOME {} {}".format(self.currentUser.get_first_name(),
                                                            self.currentUser.get_last_name()))
            all_users.clear()

    def extract_advisors(self):
        lecturer_only = []
        for lecturer in self.domain.getLecturerCreator().getLecturers():
            if not isinstance(lecturer, Advisor):
                lecturer_only.append(lecturer)
        return lecturer_only

    def logout(self):
        self.userInterface.resetPages()
        login = LoginPage("Welcome! Please enter your username/password.")
        self.userInterface.addPage(login)
        self.userInterface.setCurrentPage("LOGIN_PAGE")
        self.setCurrentUser(None)

    def exit(self):
        self.updateStudentJson()
        self.updateCourseJson()
        self.updateAdvisorJson()
        self.updateLecturerJson()
        exit(0)

    def updateCourseJson(self):
        try:
            with open("JSON_Files\\courses.json", 'r') as file:
                content = file.read()
                jsonObject = json.loads(content)
                courseJSON = jsonObject["courses"]
                for i in range(len(courseJSON)):
                    currentCourse = courseJSON[i]
                    currentCourse["quota"] = self.domain.getCourseCreator().getCourses()[i].getQuota()
                    currentCourse["studentList"] = self.studentToJsonArray(
                        self.domain.getCourseCreator().getCourses()[i].getStudentList())
            with open("JSON_Files\\courses.json", 'w') as file:
                file.write(json.dumps(jsonObject, indent=4))
        except (IOError, json.JSONDecodeError):
            print("An error occurred while writing data to the courses JSON file.")

    def updateLecturerJson(self):
        try:
            with open("JSON_Files\\lecturers.json", 'r') as file:
                content = file.read()
                jsonObject = json.loads(content)
                lecturerJSON = jsonObject["lecturers"]
                for i in range(len(self.domain.getLecturerCreator().getLecturers())):
                    if not isinstance(self.domain.getLecturerCreator().getLecturers()[i], Advisor):
                        currentLecturer = lecturerJSON[i]
                        currentLecturer["password"] = self.domain.getLecturerCreator().getLecturers()[
                            i].getPassword().getPassword()
            with open("JSON_Files\\lecturers.json", 'w') as file:
                file.write(json.dumps(jsonObject, indent=4))
        except (IOError, json.JSONDecodeError):
            print("An error occurred while writing data to the lecturers JSON file.")

    def updateAdvisorJson(self):
        try:
            with open("JSON_Files\\advisors.json", 'r') as file:
                content = file.read()
                jsonObject = json.loads(content)
                advisorJSON = jsonObject["advisors"]
                for i in range(len(self.domain.getAdvisorCreator().getAdvisors())):
                    currentAdvisor = advisorJSON[i]
                    currentAdvisor["password"] = self.domain.getAdvisorCreator().getAdvisors()[
                        i].getPassword().getPassword()
            with open("JSON_Files\\advisors.json", 'w') as file:
                file.write(json.dumps(jsonObject, indent=4))
        except (IOError, json.JSONDecodeError):
            print("An error occurred while writing data to the advisors JSON file.")

    def updateStudentJson(self):
        for i in range(len(self.domain.getStudentCreator().getStudents())):
            try:
                studentId = self.domain.getStudentCreator().getStudents()[i].getPersonId().getId()
                with open(f"JSON_Files\\Students\\{studentId}.json", 'r') as file:
                    content = file.read()
                    jsonStudent = json.loads(content)
                    registration = jsonStudent["registration"]
                    selected = self.domain.getStudentCreator().getStudents()[i].getSelectedCourses()
                    approved = self.domain.getStudentCreator().getStudents()[i].getApprovedCourses()
                    registration["selectedCourses"] = self.transcriptCourses(selected)
                    registration["approvedCourses"] = self.transcriptCourses(approved)
                    jsonStudent["password"] = self.domain.getStudentCreator().getStudents()[
                        i].getPassword().getPassword()
                    jsonStudent["request"] = self.domain.getStudentCreator().getStudents()[i].getRequest()
                    jsonStudent["readNotification"] = list(
                        self.domain.getStudentCreator().getStudents()[i].getReadNotifications())
                    jsonStudent["unreadNotification"] = list(
                        self.domain.getStudentCreator().getStudents()[i].getUnreadNotifications())
                with open(f"JSON_Files\\Students\\{studentId}.json", 'w') as file:
                    file.write(json.dumps(jsonStudent, indent=4))
            except (IOError, json.JSONDecodeError):
                print("\nSYSTEM UPDATE FAILS.")

    def transcriptCourses(self, transcript_courses_list):
        transcript_courses_ar = []
        for course in transcript_courses_list:
            if isinstance(course, CourseSession):
                transcript_courses_ar.append(f"{course.getCourseId().getId()}.{course.getSessionId().getId()}")
            else:
                transcript_courses_ar.append(course.getCourseId().getId())
        return transcript_courses_ar

    def studentToJsonArray(self, students):
        student_ids = [student.getPersonId().getId() for student in students]
        return student_ids

    def listenUserInterface(self, sm):
        functionType = sm.getFunctionType()

        if functionType == "LOGIN":
            userInfo = sm.getInput()
            self.login(userInfo)

        elif functionType == "LOGOUT":
            print(
                "LOGOUT SUCCESSFUL - GOODBYE " + self.currentUser.getFirstName() + " " + self.currentUser.getLastName())
            print("bunu renkli yazcan he unutma dayıogli")
            self.logout()
            self.userInterface.setCurrentPage("LOGIN_PAGE")

        elif functionType == "EXIT":
            print("SYSTEM EXITING")
            time.sleep(0.5)
            print("SYSTEM EXITING.")
            time.sleep(0.5)
            print("SYSTEM EXITING..")
            time.sleep(0.5)
            print("SYSTEM EXITING...")
            time.sleep(0.5)
            print("bunu renkli yazcan he unutma dayıogli")
            self.exit()

        elif functionType == "CHANGE_PAGE":
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "SELECT_COURSE":
            student = self.currentUser
            courseName = student.getSelectableCourses()[int(sm.getInput()) - 1].getCourseName()
            if student.addSelectedCourse(int(sm.getInput())):
                print("Course Addition Is Succesful - " + courseName)
                print("bunu renkli yazcan he unutma dayıogli")
            else:
                print("Course Addition Is Not Succesful - " + courseName)
                print("bunu renkli yazcan he unutma dayıogli")

            selectableCoursePage = self.userInterface.selectPage("SELECTABLE_COURSES_PAGE")
            selectableCoursePage.setContent(
                self.domain.getPageCreator().createSelectableCoursesPageContent(student.getSelectableCourses(),
                                                                                student.getMarks()))
            selectableCoursePage.setNumberOfSelectableCourses(len(student.getSelectableCourses()))

            selectedCoursePage = self.userInterface.selectPage("SELECTED_COURSES_PAGE")
            selectedCoursePage.setContent(
                self.domain.getPageCreator().createSelectedCoursesPageContent(student.getSelectedCourses()))
            selectedCoursePage.setNumberOfDropableCourses(len(student.getSelectedCourses()))

            syllabus = self.userInterface.selectPage("SYLLABUS_PAGE")
            syllabus.setContent(self.domain.getPageCreator().createSyllabusPageContent(student.getSyllabus()))

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "DROP_COURSE":
            student = self.currentUser
            courseName = student.getSelectableCourses()[int(sm.getInput()) - 1].getCourseName()
            student.dropCourse(int(sm.getInput()))
            print("Course Dropping Is Succesful - " + courseName)
            print("bunu renkli yazcan he unutma dayıogli")

            selectableCoursePage = self.userInterface.selectPage("SELECTABLE_COURSES_PAGE")
            selectableCoursePage.setContent(
                self.domain.getPageCreator().createSelectableCoursesPageContent(student.getSelectableCourses(),
                                                                                student.getMarks()))
            selectableCoursePage.setNumberOfSelectableCourses(len(student.getSelectableCourses()))

            selectedCoursePage = self.userInterface.selectPage("SELECTED_COURSES_PAGE")
            selectedCoursePage.setContent(
                self.domain.getPageCreator().createSelectedCoursesPageContent(student.getSelectedCourses()))
            selectedCoursePage.setNumberOfDropableCourses(len(student.getSelectedCourses()))

            syllabus = self.userInterface.selectPage("SYLLABUS_PAGE")
            syllabus.setContent(self.domain.getPageCreator().createSyllabusPageContent(student.getSyllabus()))

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "SEND_APPROVE":
            student = self.currentUser
            if student.getRequest() == "false":
                student.sendToApproval()
                print("You have successfully sent your course selection list for advisor approval!")
                print("bunu renkli yazcan he unutma dayıogli")
            else:
                print("You have already successfully sent your course selection list for advisor approval!")
                print("bunu renkli yazcan he unutma dayıogli")
                print("bunu renkli yazcan he unutma dayıogli")

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "SELECET_STUDENT":
            advisor = self.currentUser
            advisor.selectStudent(int(sm.getInput()))

            selectedStudentRequestPage = self.userInterface.selectPage("SELECTED_STUDENT_REQUEST_PAGE")
            selectedStudentRequestPage.setContent(
                self.domain.getPageCreator().createSelectedStudentsRequestPageContent(advisor.getSelectStudent()))
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "APPROVE_REQUEST":
            advisor = self.currentUser
            selectedStudentFullName = (advisor.getSelectStudent().getFirstName() + " " +
                                       advisor.getSelectStudent().getLastName())
            advisor.sendNotification(sm.getInput(), "A")
            advisor.approve()

            print("Request Has Been Approved - " + selectedStudentFullName + "'s Request")
            print("bunu renkli yazcan he unutma dayıogli")

            selectedStudentRequestPage = self.userInterface.selectPage("SELECTED_STUDENT_REQUEST_PAGE")
            selectedStudentRequestPage.setContent(
                self.domain.getPageCreator().createSelectedStudentsRequestPageContent(advisor.getSelectStudent()))

            evaluateRequestPage = self.userInterface.selectPage("EVALUATE_REQUESTS_PAGE")
            evaluateRequestPage.setContent(
                self.domain.getPageCreator().createEvaluateRequestPageContent(advisor.getAwaitingStudents()))
            evaluateRequestPage.setNumberOfRequest(len(advisor.getAwaitingStudents()))
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "DISAPPREOVE_REQUEST":
            advisor = self.currentUser
            selectedStudentFullName = (advisor.getSelectStudent().getFirstName() + " " +
                                       advisor.getSelectStudent().getLastName())
            advisor.sendNotification(sm.getInput(), "R")
            advisor.disapprove()

            print("Request Has Been Disapproved - " + selectedStudentFullName + "'s Request")
            print("bunu renkli yazcan he unutma dayıogli")
            selectedStudentRequestPage = self.userInterface.selectPage("SELECTED_STUDENT_REQUEST_PAGE")
            selectedStudentRequestPage.setContent(
                self.domain.getPageCreator().createSelectedStudentsRequestPageContent(advisor.getSelectStudent()))

            evaluateRequestPage = self.userInterface.selectPage("EVALUATE_REQUESTS_PAGE")
            evaluateRequestPage.setContent(
                self.domain.getPageCreator().createEvaluateRequestPageContent(advisor.getAwaitingStudents()))
            evaluateRequestPage.setNumberOfRequest(len(advisor.getAwaitingStudents()))
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "SELECT_MY_COURSE":
            lecturer = self.currentUser
            lecturer.selectCourse(int(sm.getInput()))

            selectedMyCoursePage = self.userInterface.selectPage("SELECTED_MY_COURSE_PAGE")
            selectedMyCoursePage.setContent(
                self.domain.getPageCreator().createSelectedMyCoursePage(lecturer.getSelectedCourse()))

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "CHANGE_PASSWORD":
            passwords = sm.getInput()

            if self.currentUser.getPassword().compareCurrentPassword(passwords[0]):
                if self.currentUser.getPassword().checkPasswordCond(passwords[1]):
                    self.currentUser.getPassword().setPassword(passwords[1])
                    print("Password Change Successful")
                    print("bunu renkli yazcan he unutma dayıogli")
                else:
                    print("Your new password must obey the rules!")
                    print("bunu renkli yazcan he unutma dayıogli")
            else:
                print("Your current password incorrect!")
                print("bunu renkli yazcan he unutma dayıogli")

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif functionType == "READ_NOTIFICATIONS":
            student = self.currentUser
            student.clearUnreadNotification()

            mainMenuPageStudent = self.userInterface.selectPage("MAIN_MENU_PAGE")
            mainMenuPageStudent.setContent(
                self.domain.getPageCreator().createMainMenuPageStudentContent(len(student.getUnreadNotifications())))

            self.userInterface.setCurrentPage(sm.getNextPageType())

    def getCurrentUser(self):
        return self.currentUser

    def setCurrentUser(self, currentUser):
        self.currentUser = currentUser
