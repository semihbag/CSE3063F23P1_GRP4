import unittest
from Lecturer import Lecturer
from Student import Student
from Id import Id
from Password import Password
from Course import Course
from CourseType import CourseType
from CourseSchedule import CourseSchedule
from Hour import Hour
from Day import Day
from Mark import Mark



class LecturerTest(unittest.TestCase):

    def test_lucterer_info(self):
        lecturer = Lecturer("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        self.assertEqual("Mustafa",lecturer.get_first_name())
        self.assertEqual("Agaoglu",lecturer.get_last_name())
        self.assertEqual("1501002",lecturer.get_person_id().get_id())
        self.assertEquals("sql112233",lecturer.get_password().get_password())
        self.assertEqual([], lecturer.getGivenCourses())
        self.assertEqual(None,lecturer.getSelectedCourse())

    def test_select_course(self):
        lecturer = Lecturer("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        lecturer.setGivenCourses([course4,course3])
        self.assertTrue(2,len(lecturer.getGivenCourses()))
        lecturer.selectCourse(2)
        self.assertEqual("Introduction to Computer Engineering",lecturer.getSelectedCourse().get_course_name())

    def test_syllabus(self):
        lecturer = Lecturer("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        lecturer.setGivenCourses([course4,course3])

        lecturer.create_syllabus(lecturer.getGivenCourses())
        self.assertFalse(lecturer.get_syllabus().is_empty(0,1))
        self.assertFalse(lecturer.get_syllabus().is_empty(1,1))
        self.assertFalse(lecturer.get_syllabus().is_empty(0,0))
        self.assertFalse(lecturer.get_syllabus().is_empty(1,0)) 
        self.assertTrue(lecturer.get_syllabus().is_empty(2,1))  

    def test_login(self):
        
        lecturer = Lecturer("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        lecturer.setGivenCourses([course4,course3])

        newId = "l1501002"
        persons = [lecturer]
        data = [newId,lecturer.get_password().get_password()]
        username = data[0]
        password = data[1]
        self.assertTrue(isinstance(lecturer,Lecturer))
        self.assertEqual(True,"l" + lecturer.get_person_id().get_id() == username)
        self.assertEqual(True,password == "sql112233")
        return_lecturer = lecturer.login([newId,lecturer.get_password().get_password()],[lecturer])
        self.assertTrue(isinstance(return_lecturer,Lecturer))
        self.assertFalse(lecturer.get_syllabus().is_empty(0,1))
        self.assertFalse(lecturer.get_syllabus().is_empty(1,1))
        self.assertFalse(lecturer.get_syllabus().is_empty(0,0))
        self.assertFalse(lecturer.get_syllabus().is_empty(1,0)) 
        self.assertTrue(lecturer.get_syllabus().is_empty(2,1)) 

if __name__ == '__main__':
    unittest.main()