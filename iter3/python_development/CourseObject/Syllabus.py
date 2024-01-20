from iter3.python_development.CourseObject.Hour import Hour
from iter3.python_development.CourseObject.Day import Day

class Syllabus:
    def __init__(self):
        self.syllabus = [[None] * 5 for _ in range(13)]

    def returnIndexDay(self, day):
        return {
            Day.MONDAY: 0,
            Day.TUESDAY: 1,
            Day.WEDNESDAY: 2,
            Day.THURSDAY: 3,
            Day.FRIDAY: 4,
        }[day]

    def returnIndexHour(self,hour):
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

    def addCourseToSyllabus(self, course):
        for courseSchedule in course.getCourseSchedules():
            day_column = self.returnIndexDay(courseSchedule.getCourseDay())
            for hour in courseSchedule.getCourseHours():
                hour_row = self.returnIndexHour(hour)
                self.syllabus[hour_row][day_column] = course

    def checkConflict(self, course):
        if self.findConflictedCourseName(course) == "-99":
            return False
        return True

    def findConflictedCourseName(self, course):
        for courseSchedule in course.getCourseSchedules():
            day_column = self.returnIndexDay(courseSchedule.getCourseDay())
            for hour in courseSchedule.getCourseHours():
                hour_row = self.returnIndexHour(hour)
                if not self.isEmpty(hour_row, day_column):
                    return self.syllabus[hour_row][day_column].getCourseName()
        return "-99"

    def fillSyllabus(self, selectedCourses):
        for selectedCourse in selectedCourses:
            for courseSchedule in selectedCourse.getCourseSchedules():
                day_column = self.returnIndexDay(courseSchedule.getCourseDay())
                for hour in courseSchedule.getCourseHours():
                    hour_row = self.returnIndexHour(hour)
                    self.syllabus[hour_row][day_column] = selectedCourse

    def removeCourseFromSyllabus(self, course):
        for courseSchedule in course.getCourseSchedules():
            day_column = self.returnIndexDay(courseSchedule.getCourseDay())
            for hour in courseSchedule.getCourseHours():
                hour_row = self.returnIndexHour(hour)
                self.syllabus[hour_row][day_column] = None

    def isEmpty(self, rowIndex, columnIndex):
        return self.syllabus[rowIndex][columnIndex] is None

    def getSyllabus(self):
        return self.syllabus

    def setSyllabus(self, syllabus):
        self.syllabus = syllabus
