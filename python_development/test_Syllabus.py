import unittest

from Syllabus import Syllabus
from Hour import Hour
from CourseSchedule import CourseSchedule
from Course import Course
from Id import Id
from Day import Day
from CourseType import CourseType

class TestSyllabus(unittest.TestCase):

    def test_friday_to_four(self):
        syllabus = Syllabus()
        course_hour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        course_hour2 = [Hour.H_13_00_13_50]
        course_schedules = [CourseSchedule(Day.MONDAY, course_hour1), CourseSchedule(Day.FRIDAY, course_hour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, course_schedules, 2, CourseType.MANDATORY)
        syllabus.add_course_to_syllabus(course1)
        self.assertEqual(syllabus.return_index_day(Day.FRIDAY), 4)

    def test_hour_conversion(self):
        syllabus = Syllabus()
        course_hour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        course_hour2 = [Hour.H_13_00_13_50]
        course_schedules = [CourseSchedule(Day.MONDAY, course_hour1), CourseSchedule(Day.FRIDAY, course_hour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, course_schedules, 2, CourseType.MANDATORY)
        syllabus.add_course_to_syllabus(course1)
        self.assertEqual(syllabus.return_index_hour(Hour.H_14_00_14_50), 5)

    def test_check_ATA121_is_in_syllabus(self):
        syllabus = Syllabus()
        course_hour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        course_hour2 = [Hour.H_13_00_13_50]
        course_schedules = [CourseSchedule(Day.MONDAY, course_hour1), CourseSchedule(Day.FRIDAY, course_hour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, course_schedules, 2, CourseType.MANDATORY)
        syllabus.add_course_to_syllabus(course1)
        self.assertFalse(syllabus.is_empty(2, 0))
        self.assertFalse(syllabus.is_empty(3, 0))
        self.assertFalse(syllabus.is_empty(4, 4))
        self.assertTrue(syllabus.is_empty(0, 0))

    def test_conflict_between_ATA_and_MBG(self):
        syllabus = Syllabus()
        course_hour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        course_hour2 = [Hour.H_13_00_13_50]
        course_schedules = [CourseSchedule(Day.MONDAY, course_hour1), CourseSchedule(Day.FRIDAY, course_hour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, course_schedules, 2, CourseType.MANDATORY)
        syllabus.add_course_to_syllabus(course1)
        course_hour3 = [Hour.H_08_30_09_20, Hour.H_10_30_11_20]
        course_schedules1 = [CourseSchedule(Day.MONDAY, course_hour3)]
        course2 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules1, 5, CourseType.MANDATORY)

        self.assertTrue(syllabus.check_conflict(course2))

    def test_remove_ATA121_from_syllabus(self):
        syllabus = Syllabus()
        course_hour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        course_hour2 = [Hour.H_13_00_13_50]
        course_schedules = [CourseSchedule(Day.MONDAY, course_hour1), CourseSchedule(Day.FRIDAY, course_hour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, course_schedules, 2, CourseType.MANDATORY)
        syllabus.add_course_to_syllabus(course1)
        ata_course = syllabus.get_syllabus()[2][0]
        syllabus.remove_course_from_syllabus(ata_course)
        self.assertTrue(syllabus.is_empty(2, 0))
        self.assertTrue(syllabus.is_empty(3, 0))
        self.assertTrue(syllabus.is_empty(4, 4))

    def test_add_initially_courses_to_syllabus(self):
        syllabus = Syllabus()
        course_hour1 = [Hour.H_10_30_11_20, Hour.H_11_30_12_20]
        course_hour2 = [Hour.H_13_00_13_50]
        course_schedules = [CourseSchedule(Day.MONDAY, course_hour1), CourseSchedule(Day.FRIDAY, course_hour2)]
        course1 = Course(Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, None, course_schedules, 2, CourseType.MANDATORY)
        syllabus.add_course_to_syllabus(course1)

        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)

        my_courses = [course3, course4]
        syllabus.fill_syllabus(my_courses)

        self.assertFalse(syllabus.is_empty(0, 0))
        self.assertFalse(syllabus.is_empty(1, 0))
        self.assertEqual(syllabus.get_syllabus()[0][0].get_course_name(), "Introduction to Modern Biology")
        self.assertEqual(syllabus.get_syllabus()[1][0].get_course_name(), "Introduction to Modern Biology")

        self.assertFalse(syllabus.is_empty(0, 1))
        self.assertFalse(syllabus.is_empty(1, 1))
        self.assertEqual(syllabus.get_syllabus()[0][1].get_course_name(), "Introduction to Computer Engineering")
        self.assertEqual(syllabus.get_syllabus()[1][1].get_course_name(), "Introduction to Computer Engineering")

if __name__ == '__main__':
    unittest.main()