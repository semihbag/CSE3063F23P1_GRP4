class Syllabus:
    def __init__(self):
        self.syllabus = [[None] * 5 for _ in range(13)]

    def return_index_day(self, day):
        return {
            "MONDAY": 0,
            "TUESDAY": 1,
            "WEDNESDAY": 2,
            "THURSDAY": 3,
            "FRIDAY": 4,
        }[day]

    def return_index_hour(self, hour):
        return {
            "H_08_30_09_20": 0,
            "H_09_30_10_20": 1,
            "H_10_30_11_20": 2,
            "H_11_30_12_20": 3,
            "H_13_00_13_50": 4,
            "H_14_00_14_50": 5,
            "H_15_00_15_50": 6,
            "H_16_00_16_50": 7,
            "H_17_00_17_50": 8,
            "H_18_00_18_50": 9,
            "H_19_00_19_50": 10,
            "H_20_00_20_50": 11,
            "H_21_00_21_50": 12,
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
