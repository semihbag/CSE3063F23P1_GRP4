from Hour import Hour
from Day import Day

class Syllabus:
    def __init__(self):
        self.syllabus = [[None] * 5 for _ in range(13)]

    def return_index_day(self, day):
        return {
            Day.MONDAY: 0,
            Day.TUESDAY: 1,
            Day.WEDNESDAY: 2,
            Day.THURSDAY: 3,
            Day.FRIDAY: 4,
        }[day]

    def return_index_hour(self,hour):
        return {
            Hour.H_08_30_09_20: 0,
            Hour.H_09_30_10_20: 1,
            Hour.H_10_30_11_20: 2,
            Hour.H_11_30_12_20: 3,
            Hour.H_13_00_13_50: 4,
            Hour.H_14_00_14_50: 5,
            Hour.H_15_00_15_50: 6,
            Hour.H_16_00_16_50: 7,
            Hour.H_17_00_17_50: 8,
            Hour.H_18_00_18_50: 9,
            Hour.H_19_00_19_50: 10,
            Hour.H_20_00_20_50: 11,
            Hour.H_21_00_21_50: 12,
    }[hour]

    def add_course_to_syllabus(self, course):
        for course_schedule in course.get_course_schedules():
            day_column = self.return_index_day(course_schedule.get_course_day())
            for hour in course_schedule.get_course_hours():
                hour_row = self.return_index_hour(hour)
                self.syllabus[hour_row][day_column] = course

    def check_conflict(self, course):
        if self.find_conflicted_course_name(course) == "-99":
            return False
        return True

    def find_conflicted_course_name(self, course):
        for course_schedule in course.get_course_schedules():
            day_column = self.return_index_day(course_schedule.get_course_day())
            for hour in course_schedule.get_course_hours():
                hour_row = self.return_index_hour(hour)
                if not self.is_empty(hour_row, day_column):
                    return self.syllabus[hour_row][day_column].get_course_name()
        return "-99"

    def fill_syllabus(self, selected_courses):
        for selected_course in selected_courses:
            for course_schedule in selected_course.get_course_schedules():
                day_column = self.return_index_day(course_schedule.get_course_day())
                for hour in course_schedule.get_course_hours():
                    hour_row = self.return_index_hour(hour)
                    self.syllabus[hour_row][day_column] = selected_course

    def remove_course_from_syllabus(self, course):
        for course_schedule in course.get_course_schedules():
            day_column = self.return_index_day(course_schedule.get_course_day())
            for hour in course_schedule.get_course_hours():
                hour_row = self.return_index_hour(hour)
                self.syllabus[hour_row][day_column] = None

    def is_empty(self, row_index, column_index):
        return self.syllabus[row_index][column_index] is None

    def get_syllabus(self):
        return self.syllabus

    def set_syllabus(self, syllabus):
        self.syllabus = syllabus
