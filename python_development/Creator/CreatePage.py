from decimal import Decimal

from python_development.Advisor import Advisor
from python_development.CourseSession import CourseSession
from python_development.CourseType import CourseType
from python_development.Lecturer import Lecturer
from python_development.Mark import Mark
from python_development.Page.AllCoursesPage import AllCoursesPage
from python_development.Page.ApprovedCoursesPage import ApprovedCoursesPage
from python_development.Page.ChangePasswordPage import ChangePasswordPage
from python_development.Page.EvaluateRequestsPage import EvaluateRequestsPage
from python_development.Page.MainMenuPageAdvisor import MainMenuPageAdvisor
from python_development.Page.MainMenuPageLecturer import MainMenuPageLecturer
from python_development.Page.MainMenuPageStudent import MainMenuPageStudent
from python_development.Page.MyCoursesPage import MyCoursesPage
from python_development.Page.MyNotificationsPage import MyNotificationsPage
from python_development.Page.MyStudentsPage import MyStudentsPage
from python_development.Page.ProfilePage import ProfilePage
from python_development.Page.SelectableCoursesPage import SelectableCoursesPage
from python_development.Page.SelectedCoursesPage import SelectedCoursesPage
from python_development.Page.SelectedMyCoursePage import SelectedMyCoursePage
from python_development.Page.SelectedStudentRequestPage import SelectedStudentRequestPage
from python_development.Page.SyllabusPage import SyllabusPage
from python_development.Page.TranscriptPage import TranscriptPage
from python_development.Student import Student


class CreatePage:

    def __init__(self):
        self.pages = []

    def create_pages(self, current_user):
        if isinstance(current_user, Student):
            main_student = MainMenuPageStudent(
                self.createMainMenuPageStudentContent(len(current_user.getUnreadNotifications())))
            self.pages.append(main_student)

            profile = ProfilePage(self.createProfilePageContent(current_user))
            self.pages.append(profile)

            c_password = ChangePasswordPage(self.createChangePasswordPage())
            self.pages.append(c_password)

            notifications = MyNotificationsPage(
                self.createMyNotificationsPageContent(current_user.getUnreadNotifications(),
                                                      current_user.getReadNotifications()))
            self.pages.append(notifications)

            transcript = TranscriptPage(self.createTranscriptPageContent(current_user))
            self.pages.append(transcript)

            syllabus = SyllabusPage(self.createSyllabusPageContent(current_user.getSyllabus()))
            self.pages.append(syllabus)

            all_courses = AllCoursesPage(self.createAllCoursesPageContent(current_user.getCurriculum()))
            self.pages.append(all_courses)

            selectable = SelectableCoursesPage(
                self.createSelectableCoursesPageContent(current_user.getSelectableCourses(),
                                                        current_user.getMarks()))
            selectable.setNumberOfSelectableCourses(len(current_user.getSelectableCourses()))
            self.pages.append(selectable)

            selected = SelectedCoursesPage(
                self.createSelectedCoursesPageContent(current_user.getSelectedCourses()))
            selected.setNumberOfDropableCourses(len(current_user.getSelectedCourses()))
            self.pages.append(selected)

            approved = ApprovedCoursesPage(
                self.createApprovedCoursesPageContent(current_user.getApprovedCourses()))
            self.pages.append(approved)

        elif isinstance(current_user, Advisor):
            main_advisor = MainMenuPageAdvisor(self.createMainMenuPageAdvisorContent())
            self.pages.append(main_advisor)

            profile = ProfilePage(self.createProfilePageContent(current_user))
            self.pages.append(profile)

            c_password = ChangePasswordPage(self.createChangePasswordPage())
            self.pages.append(c_password)

            my_students = MyStudentsPage(self.createMyStudentsPageContent(current_user.getStudentList()))
            self.pages.append(my_students)

            evaluate_request = EvaluateRequestsPage(
                self.createEvaluateRequestPageContent(current_user.getAwaitingStudents()))
            evaluate_request.setNumberOfRequest(len(current_user.getAwaitingStudents()))
            self.pages.append(evaluate_request)

            my_courses = MyCoursesPage(self.createMyCoursesPageContent(current_user))
            my_courses.setNumberOfCourses(len(current_user.getGivenCourses()))
            self.pages.append(my_courses)

            selected_course = SelectedMyCoursePage(
                self.createSelectedMyCoursePage(current_user.getSelectedCourse()))
            self.pages.append(selected_course)

            selected_student_request = SelectedStudentRequestPage(
                self.createSelectedStudentsRequestPageContent(current_user.getSelectStudent()))
            self.pages.append(selected_student_request)

            syllabus = SyllabusPage(self.createSyllabusPageContent(current_user.getSyllabus()))
            self.pages.append(syllabus)

        elif isinstance(current_user, Lecturer):
            main_lecturer = MainMenuPageLecturer(self.createMainMenuPageLecturerContent())
            self.pages.append(main_lecturer)

            profile = ProfilePage(self.createProfilePageContent(current_user))
            self.pages.append(profile)

            c_password = ChangePasswordPage(self.createChangePasswordPage())
            self.pages.append(c_password)

            my_courses = MyCoursesPage(self.createMyCoursesPageContent(current_user))
            my_courses.setNumberOfCourses(len(current_user.getGivenCourses()))
            self.pages.append(my_courses)

            selected_course = SelectedMyCoursePage(
                self.createSelectedMyCoursePage(current_user.getSelectedCourse()))
            self.pages.append(selected_course)

            syllabus = SyllabusPage(self.createSyllabusPageContent(current_user.getSyllabus()))
            self.pages.append(syllabus)

        return self.pages

    # CONTENTS
    def createMainMenuPageStudentContent(self, number_of_unread_notifications):
        content = ""
        if number_of_unread_notifications != 0:
            content += "\033[32mYou have " + str(number_of_unread_notifications) + " unread messages\n\033[0m"
        content += "\033[1m         MAIN MENU         \n\033[0m" \
                   "1) Profile\n" \
                   "2) Notifications\n" \
                   "3) Transcript\n" \
                   "4) My Weekly Syllabus\n" \
                   "5) All Courses\n" \
                   "6) Approved Courses\n" \
                   "7) Offered Courses\n" \
                   "8) Selected Courses\n" \
                   "9) Log out\n" \
                   "10) Exit"
        return content

    def createMainMenuPageAdvisorContent(self):
        content = "\033[1m         MAIN MENU         \033[0m\n" \
                  "1) Profile\n" \
                  "2) Advised Student Information\n" \
                  "3) Evaluate Requests\n" \
                  "4) Courses\n" \
                  "5) My Weekly Syllabus\n" \
                  "6) Log out\n" \
                  "7) Exit\n"
        return content

    def createMainMenuPageLecturerContent(self):
        content = "\033[1m         MAIN MENU         \033[0m\n" \
                  "1) Profile\n" \
                  "2) Courses\n" \
                  "3) My Weekly Syllabus\n" \
                  "4) Log out\n" \
                  "5) Exit\n"
        return content

    def createSelectedStudentsRequestPageContent(self, student):
        content = ""
        if student is None:
            content = "No course has been selected yet.\nQuit: Q"
        else:
            content = ("\nRequests of {} {} {}\n{}\nApprove: A\nReject: R\nQuit: Q\n"
                       .format(student.getFirstName(), student.getLastName(),
                               student.getPersonId().getId(),
                               self.courseListForContent(student.getSelectedCourses(), 0, None)))
        return content

    def createEvaluateRequestPageContent(self, students):
        content = ""
        if len(students) == 0:
            content = "No request to show.\nQuit: Q"
        else:
            for i, student in enumerate(students):
                content += "{}) {} - {} {}\n".format(i + 1, student.getPersonId().getId(),
                                                     student.getFirstName(), student.getLastName())
            content += "\nSelect one of student.\nQuit: Q"
        return content

    def createMyStudentsPageContent(self, students):
        content = "\033[1mSTUDENT ID        NAME SURNAME                  TERM\n\033[0m"
        for student in students:
            full_name = student.getFirstName() + " " + student.getLastName()
            content += "{}         {}{}\n".format(student.getPersonId().getId(),
                                                  full_name,
                                                  self.blankAfterStr(full_name, 30) + " " +
                                                  str(student.getTranscript().getTerm()))
        content += "\nPress any key to return.\n"
        return content

    def createSelectableCoursesPageContent(self, courses, marks):
        content = ""
        if not courses:
            content = "No course to show.\nQ: Quit"
        else:
            content = self.courseListForContent(courses, 2, marks) + \
                      "\n\nTo select a course, press the number of the courses offered.\n\nQ: Quit"
        return content

    def createSelectedCoursesPageContent(self, courses):
        content = ""
        if not courses:
            content = "No course to show.\nQ: Quit"
        else:
            content = self.courseListForContent(courses, 0, None) + \
                      "\nPress number to drop course \nA: Send to approval\nQ: Quit"
        return content

    def createAllCoursesPageContent(self, courses):
        content = self.courseListForContent(courses, 1, None) + \
                  "\n\nPress any key to return.\n"
        return content

    def createApprovedCoursesPageContent(self, courses):
        content = self.courseListForContent(courses, 0, None) + \
                  "\nPress any key to return.\n"
        return content

    def createProfilePageContent(self, user):
        content = ""
        if isinstance(user, Student):
            content += "\033[1mSTUDENT {}\n\033[0m".format(user.getPersonId().getId())
        elif isinstance(user, Advisor):
            content += "\033[1mADVISOR {}\n\033[0m".format(user.getPersonId().getId())
        elif isinstance(user, Lecturer):
            content += "\033[1mLECTURER {}\n\033[0m".format(user.getPersonId().getId())

        content += "Name      : {}\nSurname   : {}\n\nC: Change password\nQ: Quit\n".format(
            user.getFirstName(), user.getLastName())
        return content

    def createChangePasswordPage(self):
        return ("• New password must be at least 8 characters, at most 20 characters long.\n" +
                "• New password must contain an upper and a lower case letter.\n" +
                "• New password must contain at least one digit.\n" +
                "• New password must contain a special character: '.' (dot), '_'(underscore)\n")

    def createMyCoursesPageContent(self, lecturer):
        content = "\033[1m\nCOURSES\n\033[0m"
        for i, course in enumerate(lecturer.getGivenCourses()):
            session_info = f".{course.getSessionId().getId()}" if isinstance(course, CourseSession) else ""
            content += (f"{i + 1})  {course.getCourseId().getId()}{session_info}" +
                        self.blankAfterStr(f"{course.getCourseId().getId()}{session_info}", 13) +
                        f"{course.getCourseName()}\n")
        content += "\nPress number to view course content.\nQ: Quit\n"
        return content

    def createSelectedMyCoursePage(self, course):
        content = "\033[1m\nAll Students:\n\033[0m"
        if course is not None:
            for student in course.getStudentList():
                content += (f"{student.getPersonId().getId()}  " +
                            f"{student.getFirstName()}" +
                            self.blankAfterStr(student.getFirstName, 20) +
                            f"{student.getLastName()}\n")
        content += "\nPress any key to return.\n"
        return content

    def createSyllabusPageContent(self, syllabus):
        content = ("\033[1m                                    TIME TABLE\n\n\033[0m" +
                   "\033[1m                   MONDAY        TUESDAY       WEDNESDAY     " +
                   "THURSDAY      FRIDAY\n\033[0m")
        table = self.courseIds(syllabus.getSyllabus())
        for i, row in enumerate(table):
            content += "\033[1m" + self.returnHour(i) + "\033[0m" + "      "
            content += "".join([f"{course}{self.blankAfterStr(course, 14)}" for course in row]) + "\n"
        content += "\nPress any key to return.\n"
        return content

    def createMyNotificationsPageContent(self, unread_notifications, read_notifications):
        content = "\033[1mNOTIFICATIONS\n\033[0m"
        if unread_notifications:
            content += "\033[1mNew\n\033[0m"
            content += "".join([f"\033[33;1m{unread}\n\033[0m" for unread in unread_notifications])
        if read_notifications:
            content += "\033[1mOld\n\033[0m"
            content += "".join([f"{read}\n" for read in read_notifications])
        if not unread_notifications and not read_notifications:
            content += "\nNo notification.\n"
        content += "\nPress any key to return.\n"
        return content

    def createTranscriptPageContent(self, student):
        content = ("\033[1m                               MARMARA UNIVERSITY\n\033[0m" +
                   ("\033[1m                                   TRANSCRIPT\n\n\033[0m" +
                    f"Student Id : {student.getPersonId().getId()}" +
                    self.blankAfterStr(f"Student Id : {student.getPersonId().getId()}", 45) +
                    "Faculty    : Engineering Faculty\n" +
                    f"Name       : {student.getFirstName()}" +
                    self.blankAfterStr(f"Name       : {student.getFirstName()}", 45) +
                    "Department : Computer Engineering\n" +
                    f"Surname    : {student.getLastName()}" +
                    self.blankAfterStr(f"Surname    : {student.getLastName()}", 45) +
                    f"GPA        : {Decimal(student.getTranscript().getGPA_100()):.2f}\n\n"))
        all_transcript_courses = student.getTranscript().getPassedCourses() + student.getTranscript().getFailedCourses()
        for j in range(1, student.getTranscript().getTerm()):
            content += f"\033[1mSEMESTER {j}\n\033[0m"
            content += ("\033[1mCODE         NAME                                              "
                        "CREDIT | ACTS       GRADE\n\033[0m")
            for all_transcript_course in all_transcript_courses:
                if all_transcript_course.getTerm() == j:
                    course = all_transcript_course.getCourse()
                    content += (f"{course.getCourseId().getId()}" +
                                self.blankAfterStr(course.getCourseId().getId(), 13) +
                                f"{course.getCourseName()}" +
                                self.blankAfterStr(course.getCourseName(), 50) +
                                f"{course.getCredit()} | {course.getCredit()}               "
                                f"{all_transcript_course.getGrade()}\n")
            content += "\n"
        content += "Press any key to return.\n"
        return content

    def courseIds(self, course_table):
        course_ids = [["   -" if course is None else
                       (f"{course.getCourseId().getId()}.{course.getSessionId().getId()}"
                        if isinstance(course, CourseSession) else course.getCourseId().getId())
                       for course in row]
                      for row in course_table]
        return course_ids

    def courseListForContent(self, courses, type, marks):
        str_content = ""
        if not courses:
            str_content = "No course to show.\n"
        else:
            for i, course in enumerate(courses):
                if i == 0 and type != 1 and not (type == 2 and course.getCourseType() != CourseType.MANDATORY):
                    str_content = ("\033[1m     CODE         COURSE                                             "
                                   "LECTURER\n\033[0m")

                if type == 1:
                    str_content += self.allCourseLabels(i)
                elif type == 2:
                    if i == 0 and (course.getCourseType() != CourseType.MANDATORY):
                        str_content += f"\033[1m\n{course.getCourseType()}\n\033[1m"
                    elif i > 0 and (course.getCourseType() != courses[i - 1].getCourseType()):
                        str_content += f"\033[1m\n{course.getCourseType()}\n\033[1m"
                        str_content += ("\033[1m     CODE         COURSE                                             "
                                        "LECTURER\n\033[0m")

                if marks:
                    mark = marks[i]
                    if mark == Mark.SELECTED:
                        str_content += "\033[32;1m"
                    elif mark == Mark.ERROR_CONFLICT:
                        str_content += "\033[31;1m"
                    elif mark == Mark.ERROR_SAME_TYPE:
                        str_content += "\033[37;1m"

                if isinstance(course, CourseSession):
                    str_content += (f"{i + 1}{self.blankAfterI(i + 1)}" +
                                    f"{course.getCourseId().getId()}.{course.getSessionId().getId()}" +
                                    self.blankAfterStr(
                                        f"{course.getCourseId().getId()}.{course.getSessionId().getId()}", 13))
                else:
                    str_content += (f"{i + 1}{self.blankAfterI(i + 1)}" +
                                    f"{course.getCourseId().getId()}" +
                                    self.blankAfterStr(f"{course.getCourseId().getId()}", 13))

                str_content += (f"{course.getCourseName()}" +
                                self.blankAfterStr(course.getCourseName(), 50) +
                                f"{course.getLecturer().getFirstName()} {course.getLecturer().getLastName()}\n")
                str_content += "\033[0m"

        return str_content

    def allCourseLabels(self, label_index):
        return {
            0: "\033[1mTERM 1\n\033[0m"
               "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            9: "\033[1m\nTERM 2\n\033[0m"
               "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            17: "\033[1m\nTERM 3\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            23: "\033[1m\nTERM 4\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            28: "\033[1m\nTERM 5\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            33: "\033[1m\nTERM 6\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            39: "\033[1m\nTERM 7\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            46: "\033[1m\nTERM 8\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            50: "\033[1m\nNON-TECHNICAL ELECTIVES\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            59: "\033[1m\nTECHNICAL ELECTIVES\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
            66: "\033[1m\nFACULTY ELECTIVES\n\033[0m"
                "\033[1m     CODE         COURSE                                             LECTURER\n\033[0m",
        }.get(label_index, "")

    def returnHour(self, hour_index):
        return {
            0: "08:30 - 09:20",
            1: "09:30 - 10:20",
            2: "10:30 - 11:20",
            3: "11:30 - 12:20",
            4: "13:00 - 13:50",
            5: "14:00 - 14:50",
            6: "15:00 - 15:50",
            7: "16:00 - 16:50",
            8: "17:00 - 17:50",
            9: "18:00 - 18:50",
            10: "19:00 - 19:50",
            11: "20:00 - 20:50",
            12: "21:00 - 21:50",
        }.get(hour_index, "")

    def blankAfterStr(self, s, space):
        return " " * (space - len(s))

    def blankAfterI(self, i):
        return "    " if i < 10 else "   "
