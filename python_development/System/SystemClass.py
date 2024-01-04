import json
import os

from python_development.Advisor import Advisor
from python_development.CourseSession import CourseSession
from python_development.Page.LoginPage import LoginPage
from python_development.Page.PageType import PageType
from python_development.System.SystemDomain import SystemDomain

from python_development.System import FunctionType
from python_development.Page import PageType
import time

class SystemClass:
    def __init__(self, user_interface):
        self.domain = SystemDomain()
        self.currentUser = None
        self.userInterface = user_interface

        login = LoginPage("Welcome! Please enter your username/password.")
        self.userInterface.addPage(login)
        self.userInterface.setCurrentPage(PageType.LOGIN_PAGE)

    def run(self):
        while True:
            self.userInterface.display()
            self.listenUserInterface(self.userInterface.getSystemMessage())

    def login(self, user_info):
        username = user_info[0]
        password = user_info[1]
        user_found = False

        if username[0] == 'o':
            for student in self.domain.getStudentCreator().getStudents():
                if ("o" + student.getPersonId().getId() == username) and (
                        student.getPassword().getPassword() == password):
                    self.set_current_user(student)
                    user_found = True
                    break
        elif username[0] == 'l':
            for lecturer in self.domain.getLecturerCreator().getLecturers():
                if ("l" + lecturer.getPersonId().getId() == username) and (
                        lecturer.getPassword().getPassword() == password):
                    if isinstance(lecturer, Advisor):
                        lecturer.findAwaitingStudents()
                    self.set_current_user(lecturer)
                    lecturer.createSyllabus(lecturer.getGivenCourses())
                    user_found = True
                    break

        if not user_found:
            print("\u001B[33;1mUsername/Password incorrect.\n\u001B[0m")
        else:
            self.userInterface.setPages(self.domain.getPageCreator().createPages(self.currentUser))
            self.userInterface.setCurrentPage(PageType.MAIN_MENU_PAGE)
            print(
                "\u001B[32;1mLOGIN SUCCESSFUL - WELCOME " + self.currentUser.getFirstName() + " " + self.currentUser.getLastName() + "\u001B[0m")

    def logout(self):
        self.userInterface.resetPages()
        login = LoginPage("Welcome! Please enter your username/password.")
        self.userInterface.addPage(login)
        self.userInterface.setCurrentPage(PageType.LOGIN_PAGE)
        self.set_current_user(None)

    def exit(self):
        self.updateStudentJson()
        self.updateCourseJson()
        self.updateAdvisorJson()
        self.updateLecturerJson()
        os._exit(0)

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
        except (IOError, json.JSONDecodeError) as ignored:
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
        except (IOError, json.JSONDecodeError) as ignored:
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
        except (IOError, json.JSONDecodeError) as ignored:
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
            except (IOError, json.JSONDecodeError) as exception:
                print("\nSYSTEM UPDATE FAILS.")

    def transcriptCourses(self, transcript_courses_list):
        transcript_courses_ar = []
        for course in transcript_courses_list:
            if isinstance(course, CourseSession):
                transcript_courses_ar.append(f"{course.getCourseId().getId()}.{course.getSessionId().getId()}")
            else:
                transcript_courses_ar.append(course.getCourseId().getId())
        return transcript_courses_ar

    def setSurrentUser(self, user):
        self.currentUser = user

    def studentToJsonArray(self, students):
        student_ids = [student.getPersonId().getId() for student in students]
        return student_ids
    


    def listenUserInterface(self, sm):
        functionType = sm.getFunctionType()

        if (functionType == FunctionType.LOGIN):
            userInfo = sm.getInput()
            self.login(userInfo)
       
        elif (functionType == FunctionType.LOGOUT):
            print("LOGOUT SUCCESSFUL - GOODBYE " + self.currentUser.getFirstName() + " " + self.currentUser.getLastName())
            print("bunu renkli yazcan he unutma dayıogli")
            self.logout()
            self.userInterface.setCurrentPage(PageType.LOGIN_PAGE)
       
        elif (functionType == FunctionType.EXIT):
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

        elif (functionType == FunctionType.CHANGE_PAGE):
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif (functionType == FunctionType.SELECT_COURSE):
            student = self.currentUser
            courseName = student.getSelectableCourses()[int(sm.getInput()) - 1].getCourseName()
            if (student.addSelectedCourse(int(sm.getInput()))):
                print("Course Addition Is Succesful - " + courseName)
                print("bunu renkli yazcan he unutma dayıogli")
            else:
                print("Course Addition Is Not Succesful - " + courseName)
                print("bunu renkli yazcan he unutma dayıogli")

            selectableCoursePage = self.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE)
            selectableCoursePage.setContent(self.domain.getPageCreator().createSelectableCoursesPageContent(student.getSelectableCourses(), student.getMarks()))
            selectableCoursePage.setNumberOfSelectableCourses(len(student.getSelectableCourses()))

            selectedCoursePage = self.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE)
            selectedCoursePage.setContent(self.domain.getPageCreator().createSelectedCoursesPageContent(student.getSelectedCourses()))
            selectedCoursePage.setNumberOfDropableCourses(len(student.getSelectedCourses()))

            syllabus = self.userInterface.selectPage(PageType.SYLLABUS_PAGE)
            syllabus.setContent(self.domain.getPageCreator().createSyllabusPageContent(student.getSyllabus()))

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif (functionType == FunctionType.DROP_COURSE):
            student = self.currentUser
            courseName = student.getSelectableCourses()[int(sm.getInput()) - 1].getCourseName()
            student.dropCourse(int(sm.getInput()))
            print("Course Dropping Is Succesful - " + courseName)
            print("bunu renkli yazcan he unutma dayıogli")

            selectableCoursePage = self.userInterface.selectPage(PageType.SELECTABLE_COURSES_PAGE)
            selectableCoursePage.setContent(self.domain.getPageCreator().createSelectableCoursesPageContent(student.getSelectableCourses(), student.getMarks()))
            selectableCoursePage.setNumberOfSelectableCourses(len(student.getSelectableCourses()))

            selectedCoursePage = self.userInterface.selectPage(PageType.SELECTED_COURSES_PAGE)
            selectedCoursePage.setContent(self.domain.getPageCreator().createSelectedCoursesPageContent(student.getSelectedCourses()))
            selectedCoursePage.setNumberOfDropableCourses(len(student.getSelectedCourses()))

            syllabus = self.userInterface.selectPage(PageType.SYLLABUS_PAGE)
            syllabus.setContent(self.domain.getPageCreator().createSyllabusPageContent(student.getSyllabus()))

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif (functionType == FunctionType.SEND_APPROVE):
            student = self.currentUser
            if (student.getRequest() == "false"):
                student.sendToApproval()
                print("You have successfully sent your course selection list for advisor approval!")
                print("bunu renkli yazcan he unutma dayıogli")
            else:
                print("You have already successfully sent your course selection list for advisor approval!")                print("bunu renkli yazcan he unutma dayıogli")
                print("bunu renkli yazcan he unutma dayıogli")

            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif (functionType == FunctionType.SELECET_STUDENT):
            advisor = self.currentUser
            advisor.selectStudent(int(sm.getInput()))

            selectedStudentRequestPage = self.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE)
            selectedStudentRequestPage.setContent(self.domain.getPageCreator().createSelectedStudentsRequestPageContent(advisor.getSelectStudent()))
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif (functionType == FunctionType.APPROVE_REQUEST):
            advisor = self.currentUser
            selectedStudentFullName = advisor.getSelectStudent().getFirstName() + " " + advisor.getSelectStudent().getLastName()
            advisor.sendNotification(sm.getInput(), "A")
            advisor.approve()
            print("Request Has Been Approved - " + selectedStudentFullName + "'s Request")
            print("bunu renkli yazcan he unutma dayıogli")

            selectedStudentRequestPage = self.userInterface.selectPage(PageType.SELECTED_STUDENT_REQUEST_PAGE)
            selectedStudentRequestPage.setContent(self.domain.getPageCreator().createSelectedStudentsRequestPageContent(advisor.getSelectStudent()))

            evaluateRequestPage = self.userInterface.selectPage(PageType.EVALUATE_REQUESTS_PAGE)
            evaluateRequestPage.setContent(self.domain.getPageCreator().createEvaluateRequestPageContent(advisor.getAwaitingStudents()))
            evaluateRequestPage.setNumberOfRequest(len(advisor.getAwaitingStudents()))
            self.userInterface.setCurrentPage(sm.getNextPageType())

        elif (functionType == FunctionType.DISAPPREOVE_REQUEST):
            advisor = self.currentUser
            selectedStudentFullName = advisor.getSelectStudent().getFirstName() + " " + advisor.getSelectStudent().getLastName()
            advisor.sendNotification(sm.getInput(), "R")
            advisor.disapprove()


