import unittest

from python_development.CourseObject.Syllabus import Syllabus
from python_development.CourseObject.Hour import Hour
from python_development.CourseObject.CourseSchedule import CourseSchedule
from python_development.CourseObject.Course import Course
from python_development.PersonObject.Id import Id
from python_development.CourseObject.Day import Day
from python_development.CourseObject.CourseType import CourseType

class TestSyllabus(unittest.TestCase):

    def testFridayToFour(self):
        syllabus = Syllabus()
        courseHour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        courseHour2 = [Hour.H_13_00_13_50]
        courseSchedules = [CourseSchedule(Day.MONDAY, courseHour1), CourseSchedule(Day.FRIDAY, courseHour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, courseSchedules, 2, CourseType.MANDATORY)
        syllabus.addCourseToSyllabus(course1)
        self.assertEqual(syllabus.returnIndexDay(Day.FRIDAY), 4)

    def testH_14_00_14_50toFive(self):
        syllabus = Syllabus()
        courseHour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        courseHour2 = [Hour.H_13_00_13_50]
        courseSchedules = [CourseSchedule(Day.MONDAY, courseHour1), CourseSchedule(Day.FRIDAY, courseHour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, courseSchedules, 2, CourseType.MANDATORY)
        syllabus.addCourseToSyllabus(course1)
        self.assertEqual(syllabus.returnIndexHour(Hour.H_14_00_14_50), 5)

    def testCheckATA121IsInSyllabus(self):
        syllabus = Syllabus()
        courseHour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        courseHour2 = [Hour.H_13_00_13_50]
        courseSchedules = [CourseSchedule(Day.MONDAY, courseHour1), CourseSchedule(Day.FRIDAY, courseHour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, courseSchedules, 2, CourseType.MANDATORY)
        syllabus.addCourseToSyllabus(course1)
        self.assertFalse(syllabus.isEmpty(2, 0))
        self.assertFalse(syllabus.isEmpty(3, 0))
        self.assertFalse(syllabus.isEmpty(4, 4))
        self.assertTrue(syllabus.isEmpty(0, 0))

    def testConflictBetweenATAandMBG(self):
        syllabus = Syllabus()
        courseHour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        courseHour2 = [Hour.H_13_00_13_50]
        courseSchedules = [CourseSchedule(Day.MONDAY, courseHour1), CourseSchedule(Day.FRIDAY, courseHour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, courseSchedules, 2, CourseType.MANDATORY)
        syllabus.addCourseToSyllabus(course1)
        courseHour3 = [Hour.H_08_30_09_20, Hour.H_10_30_11_20]
        courseSchedules1 = [CourseSchedule(Day.MONDAY, courseHour3)]
        course2 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, courseSchedules1, 5, CourseType.MANDATORY)

        self.assertTrue(syllabus.checkConflict(course2))

    def testRemoveATA121FromSyllabus(self):
        syllabus = Syllabus()
        courseHour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        courseHour2 = [Hour.H_13_00_13_50]
        courseSchedules = [CourseSchedule(Day.MONDAY, courseHour1), CourseSchedule(Day.FRIDAY, courseHour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, courseSchedules, 2, CourseType.MANDATORY)
        syllabus.addCourseToSyllabus(course1)
        ataCourse = syllabus.getSyllabus()[2][0]
        syllabus.removeCourseFromSyllabus(ataCourse)
        self.assertTrue(syllabus.isEmpty(2, 0))
        self.assertTrue(syllabus.isEmpty(3, 0))
        self.assertTrue(syllabus.isEmpty(4, 4))

    def testAddInitiallyCoursesToTheSyllabus(self):
        syllabus = Syllabus()
        courseHour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        courseHour2 = [Hour.H_13_00_13_50]
        courseSchedules = [CourseSchedule(Day.MONDAY, courseHour1), CourseSchedule(Day.FRIDAY, courseHour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, courseSchedules, 2, CourseType.MANDATORY)
        syllabus.addCourseToSyllabus(course1)

        courseHour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        courseSchedules4 = [CourseSchedule(Day.MONDAY, courseHour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, courseSchedules4, 5, CourseType.MANDATORY)

        courseHour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        courseSchedules3 = [CourseSchedule(Day.TUESDAY, courseHour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, courseSchedules3, 4, CourseType.MANDATORY)

        myCourses = [course3, course4]
        syllabus.fillSyllabus(myCourses)

        self.assertFalse(syllabus.isEmpty(0, 0))
        self.assertFalse(syllabus.isEmpty(1, 0))
        self.assertEqual(syllabus.getSyllabus()[0][0].getCourseName(), "Introduction to Modern Biology")
        self.assertEqual(syllabus.getSyllabus()[1][0].getCourseName(), "Introduction to Modern Biology")

        self.assertFalse(syllabus.isEmpty(0, 1))
        self.assertFalse(syllabus.isEmpty(1, 1))
        self.assertEqual(syllabus.getSyllabus()[0][1].getCourseName(), "Introduction to Computer Engineering")
        self.assertEqual(syllabus.getSyllabus()[1][1].getCourseName(), "Introduction to Computer Engineering")

