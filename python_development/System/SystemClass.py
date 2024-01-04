import json
import os

from python_development.Advisor import Advisor
from python_development.CourseSession import CourseSession
from python_development.Page.LoginPage import LoginPage
from python_development.Page.PageType import PageType
from python_development.System.SystemDomain import SystemDomain


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
            self.listen_user_interface(self.userInterface.getSystemMessage())

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