import json

from python_development.Id import Id
from python_development.Password import Password
from python_development.Student import Student
from python_development.Transcript import Transcript


class CreateStudent:
    def __init__(self, file_name, students_file, courses, advisors):
        self.students = []
        self.file_name = file_name
        self.students_file = students_file
        self.create_students(courses, advisors)

    def create_students(self, courses, advisors):
        with open(self.students_file, 'r') as all_student_files:
            for line in all_student_files:
                try:
                    with open(self.file_name + line.strip(), 'r') as student_file:
                        content = student_file.read()
                        json_student = json.loads(content)
                        transcript = json_student['transcript']
                        registration = json_student['registration']

                        student_id = json_student['id']
                        name = json_student['name']
                        last_name = json_student['lastname']
                        advisor_id = json_student['advisor']
                        boolean_string = json_student['request']
                        read_notification = json_student['readNotification']
                        unread_notification = json_student['unreadNotification']
                        password = json_student['password']

                        failed_courses = transcript['failedCourses']
                        completed_courses = transcript['passedCourses']
                        grades_passed = transcript['gradesPassed']
                        grades_failed = transcript['gradesFailed']
                        term_passed = transcript['termPassed']

                        selected_courses = registration['selectedCourses']
                        approved_courses = registration['approvedCourses']

                        failed_courses_list = self.set_transcript_courses(failed_courses, grades_failed, term_passed, courses)
                        passed_courses_list = self.set_transcript_courses(completed_courses, grades_passed, term_passed, courses)

                        selected_courses_list = self.set_student_courses(selected_courses, courses)
                        approved_courses_list = self.set_student_courses(approved_courses, courses)

                        term = transcript['term']
                        gpa = self.calculate_gpa(passed_courses_list, failed_courses_list)

                        advisor = self.find_advisor(advisor_id, advisors)

                        crt_student = Student(name, last_name, Id(student_id), Password(password), advisor,
                                              Transcript(gpa, term, passed_courses_list, failed_courses_list), courses)

                        crt_student.set_request(boolean_string)
                        crt_student.set_read_notifications(list(read_notification))
                        crt_student.set_unread_notifications(list(unread_notification))
                        crt_student.set_selected_courses(selected_courses_list)
                        crt_student.set_approved_courses(approved_courses_list)
                        crt_student.filter_courses()
                        self.students.append(crt_student)

                except (json.JSONDecodeError, FileNotFoundError):
                    pass

        self.assign_students_to_advisor(advisors)
        self.fill_student_list_course(courses)