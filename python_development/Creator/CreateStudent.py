import json
import logging

from python_development.CourseSession import CourseSession
from python_development.CourseType import CourseType
from python_development.Grade import Grade
from python_development.GradeClass import GradeClass
from python_development.Id import Id
from python_development.Password import Password
from python_development.Student import Student
from python_development.Transcript import Transcript


class CreateStudent:
    def __init__(self, fileName, studentsFile, courses, advisors):
        self.students = []
        self.fileName = fileName
        self.studentsFile = studentsFile
        self.createStudents(courses, advisors)

    def createStudents(self, courses, advisors):
        with open(self.studentsFile, 'r') as all_student_files:
            for line in all_student_files:
                try:
                    with open(self.fileName + line.strip(), 'r') as student_file:
                        json_student = json.load(student_file)
                        transcript = json_student['transcript']
                        registration = json_student['registration']

                        studentId = json_student['id']
                        name = json_student['name']
                        lastname = json_student['lastname']

                        advisor_id = json_student['advisor']
                        boolean_string = json_student['request']
                        read_notification = json_student['readNotification']
                        unread_notification = json_student['unreadNotification']
                        password = json_student['password']

                        failed_courses_ar = transcript['failedCourses']
                        completed_courses_ar = transcript['passedCourses']
                        grades_passed = transcript['gradesPassed']
                        grades_failed = transcript['gradesFailed']
                        term_passed = transcript['termPassed']

                        selected_courses_ar = registration['selectedCourses']
                        approved_courses_ar = registration['approvedCourses']

                        failed_courses = self.setTranscriptCourses(failed_courses_ar, grades_failed,
                                                                   term_passed, courses)
                        passed_courses = self.setTranscriptCourses(completed_courses_ar, grades_passed,
                                                                   term_passed, courses)

                        selected_courses = self.setStudentCourses(selected_courses_ar, courses)
                        approved_courses = self.setStudentCourses(approved_courses_ar, courses)

                        term = transcript['term']
                        gpa = self.calculateGPA(passed_courses, failed_courses)

                        crt_student = Student(name, lastname, Id(studentId), Password(password),
                                              self.findAdvisor(advisor_id, advisors),
                                              Transcript(gpa, term, passed_courses, failed_courses), courses)

                        crt_student.request = boolean_string
                        crt_student.readNotifications = list(read_notification)
                        crt_student.unreadNotifications = list(unread_notification)
                        crt_student.selectedCourses = selected_courses
                        crt_student.approvedCourses = approved_courses
                        crt_student.filterCourses()
                        self.students.append(crt_student)
                except (json.JSONDecodeError, FileNotFoundError) as e:
                    logging.exception(f"Error in student data {line}: {e}")
                    pass

        self.assignStudentToAdvisor(advisors)
        self.fillStudentListCourse(courses)

    def setTranscriptCourses(self, transcript_courses, grades, term_passed, courses):
        transcript_course_list = []
        j = 0
        for i in range(len(transcript_courses)):
            for course in courses:
                if (course.getCourseId().getId() == transcript_courses[i] and
                        not self.courseExists(course, transcript_course_list)):
                    grade_class = GradeClass(course, self.getCourseGrade(grades[i]))
                    if (course.getCourseType() == CourseType.NONTECHNICAL or
                            "Is Sagligi ve Guvenligi" in course.getCourseName()):
                        grade_class.term = term_passed[j]
                        j += 1
                    else:
                        grade_class.term = course.getTerm()
                    transcript_course_list.append(grade_class)
        return transcript_course_list

    def setStudentCourses(self, student_courses_ar, courses):
        student_courses_list = []
        for s in student_courses_ar:
            for j in range(len(courses)):
                if isinstance(courses[j], CourseSession) and (
                        courses[j].getCourseId().getId() + "." + courses[j].getSessionId().getId() == s):
                    student_courses_list.append(courses[j])
                elif s == courses[j].getCourseId().getId():
                    student_courses_list.append(courses[j])
        return student_courses_list

    def findAdvisor(self, advisor_id, advisors):
        for advisor in advisors:
            if advisor.getPersonId().getId() == advisor_id:
                return advisor
        return None

    def assignStudentToAdvisor(self, advisors):
        for student in self.students:
            for advisor in advisors:
                if student.getAdvisor().getPersonId().getId() == advisor.getPersonId().getId():
                    advisor.getStudentList().append(student)
                    break

    def fillStudentListCourse(self, courses):
        with open("JSON_Files\\courses.json", 'r') as courses_file:
            course_json = json.load(courses_file)
            for i in range(len(course_json['courses'])):
                current_course = course_json['courses'][i]
                course_students_id = current_course['studentList']
                for curr_student_id in course_students_id:
                    for st in self.students:
                        if st.getPersonId().getId() == curr_student_id and st not in courses[i].getStudentList():
                            courses[i].getStudentList().append(st)
                            break

    def courseExists(self, course, transcript_course_list):
        for grade_class in transcript_course_list:
            if course.getCourseId().getId() == grade_class.getCourse().getCourseId().getId():
                return True
        return False

    def calculateGPA(self, passed_courses, failed_courses):
        gpa = 0
        total_credit = 0
        for current in passed_courses:
            gpa += current.course.credit * self.letterToGrade(current.grade)
            total_credit += current.course.credit
        for current in failed_courses:
            gpa += current.course.credit * self.letterToGrade(current.grade)
            total_credit += current.course.credit
        if total_credit == 0:
            return 0
        else:
            return gpa / total_credit

    def letterToGrade(self, grade):
        return {
            Grade.AA: 4.0,
            Grade.BA: 3.5,
            Grade.BB: 3.0,
            Grade.CB: 2.5,
            Grade.CC: 2.0,
            Grade.DC: 1.5,
            Grade.DD: 1.0,
            Grade.FD: 0.5,
            Grade.FF: 0.0,
            Grade.DZ: 0.0
        }[grade]

    def getCourseGrade(self, str_grade):
        return {
            "AA": Grade.AA,
            "BA": Grade.BA,
            "BB": Grade.BB,
            "CB": Grade.CB,
            "CC": Grade.CC,
            "DC": Grade.DC,
            "DD": Grade.DD,
            "FD": Grade.FD,
            "FF": Grade.FF,
            "DZ": Grade.DZ
        }[str_grade.upper()]

    def getStudents(self):
        return self.students
