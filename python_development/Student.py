from enum import Enum

import CourseSession
import CourseType
import Person
import Mark

class Student(Person):
    def __init__(self, first_name, last_name, student_id, password, advisor, transcript, curriculum):
        super().__init__(first_name, last_name, student_id, password)
        self.advisor = advisor
        self.transcript = transcript
        self.selected_course_credit = 0
        self.marks = []
        self.unread_notifications = []
        self.read_notifications = []
        self.selectable_courses = []
        self.selected_courses = []
        self.approved_courses = []
        self.curriculum = curriculum
        self.request = None

    def filter_courses(self):
        if self.approved_courses:
            self.create_syllabus(self.approved_courses)
        else:
            self.create_syllabus(self.selected_courses)

        for course in self.curriculum:
            if not self.is_selected_course(course) and not self.is_passed_course(course) \
                    and self.is_prerequisite_courses_passed(course) and self.is_under_quota(course) \
                    and (self.check_course_type(course) or self.is_failed_course(course)):
                self.selectable_courses.append(course)
        self.set_marks_initial()

    def login(self, user_info, persons):
        username, password = user_info
        for person in persons:
            if f"o{person.person_id}" == username and person.password.password == password:
                return person
        return None

    def is_selected_course(self, course):
        return course in self.selected_courses

    def is_passed_course(self, course):
        return any(grade_class.course == course for grade_class in self.transcript.passed_courses)

    def is_prerequisite_courses_passed(self, course):
        return all(self.is_passed_course(pre_course) for pre_course in course.prerequisite_courses)

    def is_under_quota(self, course):
        return course.quota > 0

    def is_failed_course(self, course):
        return any(failed_course.course == course and self.transcript.term % 2 == course.term % 2
                   for failed_course in self.transcript.failed_courses)

    def add_selectable_course(self, course):
        self.selectable_courses.append(course)

    def add_approved_course(self, course):
        self.approved_courses.append(course)

    def drop_all_selected_courses(self):
        self.selected_courses.clear()
        self.selected_course_credit = 0
        self.set_marks()

    def send_to_approval(self):
        self.request = "true"

    def add_selected_course(self, i):
        course = self.selectable_courses[i - 1]
        mark = self.final_check_selected_course(course)

        if mark == Mark.SUCCESSFUL:
            self.selected_courses.append(course)
            self.selected_course_credit += course.credit
            course.quota -= 1
            self.syllabus.add_course_to_syllabus(course)
            self.set_marks()
            return True

        if mark == Mark.ERROR_ALREADY_SENDED:
            print("You have already sent a request to your advisor!")

        if mark == Mark.ERROR_CREDIT_LIMIT:
            print("You exceed the selectable course credit limit!!")

        if mark == Mark.ERROR_CONFLICT:
            print(
                f"The course you want to add {course.course_name} conflicts with {self.syllabus.find_conflicted_course_name(course)}!")

        if mark == Mark.ERROR_SAME_TYPE:
            print(f"You cannot select {course.course_type} course more than once in one term!")

        if mark == Mark.SELECTED:
            if isinstance(course, CourseSession):
                print(f"You have already selected a session from {course.Course.get_course_name()}.")
            else:
                print(f"You have already selected {course.course_name}.")

        return False

    def set_marks(self):
        self.marks.clear()
        for temp_course in self.selectable_courses:
            if self.is_selected_course(temp_course):
                self.marks.append(Mark.SELECTED)
                continue
            self.marks.append(self.final_check_selected_course(temp_course))

    def set_marks_initial(self):
        for _ in self.selectable_courses:
            self.marks.append(Mark.SUCCESSFUL)

    def final_check_selected_course(self, course):
        if self.request == "false":
            if not self.is_selected_course(course):
                if self.selected_course_credit + course.credit < 40:
                    course_type = course.course_type
                    if course_type != CourseType.CourseType.MANDATORY:
                        if self.exceed_term(course_type):
                            return Mark.ERROR_SAME_TYPE
                    if not self.syllabus.check_conflict(course):
                        return Mark.SUCCESSFUL
                    else:
                        return Mark.ERROR_CONFLICT
                return Mark.ERROR_CREDIT_LIMIT
            return Mark.SELECTED
        return Mark.ERROR_ALREADY_SENDED

    def check_course_type(self, course):
        course_type = course.course_type
        if course_type == CourseType.CourseType.NONTECHNICAL and self.transcript.term >= 2:
            if not self.exceed(course_type, 2):
                return True
        elif course_type == CourseType.CourseType.TECHNICAL:
            if self.transcript.term == 7 or self.transcript.term == 8:
                return True
        elif course_type == CourseType.CourseType.FACULTY:
            if (self.transcript.term == 7 or self.transcript.term == 8) and not self.exceed(course_type, 1):
                return True
        elif course_type == CourseType.CourseType.MANDATORY:
            if course.course_name == "Engineering Project I" or course.course_name == "Engineering Project II":
                if self.transcript.total_credit >= 165:
                    return True
                return False
            elif course.term == self.transcript.term:
                return True
            elif (self.transcript.gpa_100 >= 3.0 and self.transcript.term >= 3) and (
                    (
                            course.course_name == "Is Sagligi ve Guvenligi I" or course.course_name == "Is Sagligi ve Guvenligi II") or
                    (course.term == self.transcript.term + 2)):
                return True
        return False

    def exceed(self, course_type, limit):
        ct = sum(1 for grade_class in self.transcript.passed_courses if grade_class.course.course_type == course_type)
        return ct == limit

    def exceed_term(self, course_type):
        ct = sum(1 for selected_course in self.selected_courses if selected_course.course_type == course_type)
        return ct > 0

    def clear_unread_notification(self):
        self.read_notifications.extend(self.unread_notifications)
        self.unread_notifications.clear()

    def add_unread_notification(self, notification):
        self.unread_notifications.append(notification)

    def drop_course(self, i):
        course = self.selected_courses.pop(i - 1)
        self.selected_course_credit -= course.credit
        self.syllabus.remove_course_from_syllabus(course)
        self.set_marks()

    def add_all_sessions(self, course):
        for curriculum_course in self.curriculum:
            if course.course_id == curriculum_course.course_id:
                if curriculum_course not in self.selectable_courses:
                    self.selectable_courses.append(curriculum_course)

    def remove_all_sessions(self, course):
        self.selectable_courses = [c for c in self.selectable_courses if c != course]

    def create_syllabus(self, courses):
        self.syllabus.fill_syllabus(courses)

    @property
    def curriculum(self):
        return self.curriculum

    @property
    def advisor(self):
        return self.advisor

    @advisor.setter
    def advisor(self, advisor):
        self.advisor = advisor

    @property
    def transcript(self):
        return self.transcript

    @transcript.setter
    def transcript(self, transcript):
        self.transcript = transcript

    @property
    def selectable_courses(self):
        return self.selectable_courses

    @property
    def selected_courses(self):
        return self.selected_courses

    @selected_courses.setter
    def selected_courses(self, selected_courses):
        self.selected_courses = selected_courses

    @property
    def approved_courses(self):
        return self.approved_courses

    @approved_courses.setter
    def approved_courses(self, approved_courses):
        self.approved_courses = approved_courses

    @property
    def request(self):
        return self.request

    @request.setter
    def request(self, request):
        self.request = request

    @property
    def selected_course_credit(self):
        return self.selected_course_credit

    @selected_course_credit.setter
    def selected_course_credit(self, selected_course_credit):
        self.selected_course_credit = selected_course_credit

    @property
    def unread_notifications(self):
        return self.unread_notifications

    @unread_notifications.setter
    def unread_notifications(self, unread_notifications):
        self.unread_notifications = unread_notifications

    @property
    def read_notifications(self):
        return self.read_notifications

    @read_notifications.setter
    def read_notifications(self, read_notifications):
        self.read_notifications = read_notifications

    @property
    def marks(self):
        return self.marks

    @marks.setter
    def marks(self, marks):
        self.marks = marks

    @selectable_courses.setter
    def selectable_courses(self, value):
        self._selectable_courses = value

    @curriculum.setter
    def curriculum(self, value):
        self._curriculum = value
