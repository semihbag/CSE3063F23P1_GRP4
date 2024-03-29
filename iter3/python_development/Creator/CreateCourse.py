import json
import logging

from iter3.python_development.CourseObject.Course import Course
from iter3.python_development.CourseObject.CourseSession import CourseSession
from iter3.python_development.PersonObject.Id import Id
from iter3.python_development.CourseObject.CourseSchedule import CourseSchedule
from iter3.python_development.CourseObject.CourseType import CourseType
from iter3.python_development.CourseObject.Day import Day
from iter3.python_development.CourseObject.Hour import Hour

class CreateCourse:
    def __init__(self, file_name, lecturers):
        self.courses = []
        self.file_name = file_name
        self.createCourses(lecturers)

    def createCourses(self, lecturers):
        try:
            with open(self.file_name, 'r') as file:
                content = file.read()
                json_object = json.loads(content)
                course_json = json_object["courses"]
                for course_data in course_json:
                    course_id = course_data["id"]
                    name = course_data["name"]
                    term = course_data["term"]
                    quota = course_data["quota"]
                    prerequisite_id = course_data["prerequisite"]
                    credit = course_data["credit"]

                    course_type_str = course_data["type"]
                    for lecturer in lecturers:
                        if lecturer.personId.getId() == course_data["lecturer"]:
                            course_lecturer = lecturer
                            break

                    course_schedule = []
                    self.fillCourseSchedule(course_data["day"], course_data["hour"], course_schedule)
                    course_type = self.setCourseType(course_type_str)
                    if course_data["isSession"]:
                        session_id = course_data["sessionId"]
                        course = CourseSession(Id(course_id), name, quota, term, course_lecturer, Id(session_id), course_schedule, credit, course_type)
                    else:
                        course = Course(Id(course_id), name, quota, term, course_lecturer, course_schedule, credit, course_type)

                    for prerequisite in prerequisite_id:
                        for crs in self.courses:
                            if crs.courseId.id == prerequisite:
                                course.prerequisiteCourses.append(crs)
                                break
                    self.courses.append(course)
            self.assignCoursesToLecturer(lecturers)
        except (json.JSONDecodeError, FileNotFoundError) as e:
            logging.exception(f"Error in course data in courses.json file: {e}")
            exit(0)

    def assignCoursesToLecturer(self, lecturers):
        for course in self.courses:
            for lecturer in lecturers:
                if course.lecturer == lecturer:
                    lecturer.getGivenCourses().append(course)


    def fillCourseSchedule(self, day_json_arr, hour_json_arr, course_schedules):
        for i, day_str in enumerate(day_json_arr):
            current_day = self.getCourseDay(day_str)
            hours = [self.getCourseHour(str(hour)) for hour in hour_json_arr[i]]
            course_schedules.append(CourseSchedule(current_day, hours))

    @staticmethod
    def getCourseDay(str_day):
        return {
            "MONDAY": Day.MONDAY,
            "TUESDAY": Day.TUESDAY,
            "WEDNESDAY": Day.WEDNESDAY,
            "THURSDAY": Day.THURSDAY,
            "FRIDAY": Day.FRIDAY
        }.get(str_day.upper(), None)

    @staticmethod
    def getCourseHour(str_hour):
        return {
            "8.30": Hour.H_08_30_09_20,
            "9.30": Hour.H_09_30_10_20,
            "10.30": Hour.H_10_30_11_20,
            "11.30": Hour.H_11_30_12_20,
            "13.00": Hour.H_13_00_13_50,
            "14.00": Hour.H_14_00_14_50,
            "15.00": Hour.H_15_00_15_50,
            "16.00": Hour.H_16_00_16_50,
            "17.00": Hour.H_17_00_17_50,
            "18.00": Hour.H_18_00_18_50,
            "19.00": Hour.H_19_00_19_50,
            "20.00": Hour.H_20_00_20_50,
            "21.00": Hour.H_21_00_21_50
        }.get(str_hour, None)

    @staticmethod
    def setCourseType(course_type_str):
        return {
            "mandatory": CourseType.MANDATORY,
            "technical": CourseType.TECHNICAL,
            "nontechnical": CourseType.NONTECHNICAL,
            "faculty": CourseType.FACULTY
        }.get(course_type_str, None)

    def getCourses(self):
        return self.courses

    def setCourses(self, courses):
        self.courses = courses
