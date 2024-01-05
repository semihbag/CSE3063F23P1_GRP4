import unittest
from python_development.PersonObject.Lecturer import Lecturer
from python_development.PersonObject.Id import Id
from python_development.PersonObject.Password import Password
from python_development.CourseObject.Course import Course
from python_development.CourseObject.CourseType import CourseType
from python_development.CourseObject.CourseSchedule import CourseSchedule
from python_development.CourseObject.Hour import Hour
from python_development.CourseObject.Day import Day



class LecturerTest(unittest.TestCase):

    def test_lucterer_info(self):
        lecturer = Lecturer("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        self.assertEqual("Mustafa",lecturer.getFirstName())
        self.assertEqual("Agaoglu",lecturer.getLastName())
        self.assertEqual("1501002",lecturer.getPersonId().getId())
        self.assertEquals("sql112233",lecturer.getPassword().getPassword())
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
        self.assertEqual("Introduction to Computer Engineering",lecturer.getSelectedCourse().getCourseName())

    def test_syllabus(self):
        lecturer = Lecturer("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        lecturer.setGivenCourses([course4,course3])

        lecturer.createSyllabus(lecturer.getGivenCourses())
        self.assertFalse(lecturer.getSyllabus().isEmpty(0,1))
        self.assertFalse(lecturer.getSyllabus().isEmpty(1,1))
        self.assertFalse(lecturer.getSyllabus().isEmpty(0,0))
        self.assertFalse(lecturer.getSyllabus().isEmpty(1,0)) 
        self.assertTrue(lecturer.getSyllabus().isEmpty(2,1))  

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
        data = [newId,lecturer.getPassword().getPassword()]
        username = data[0]
        password = data[1]
        self.assertTrue(isinstance(lecturer,Lecturer))
        self.assertEqual(True,"l" + lecturer.getPersonId().getId() == username)
        self.assertEqual(True,password == "sql112233")
        return_lecturer = lecturer.login([newId,lecturer.getPassword().getPassword()],[lecturer])
        self.assertTrue(isinstance(return_lecturer,Lecturer))
        self.assertFalse(lecturer.getSyllabus().isEmpty(0,1))
        self.assertFalse(lecturer.getSyllabus().isEmpty(1,1))
        self.assertFalse(lecturer.getSyllabus().isEmpty(0,0))
        self.assertFalse(lecturer.getSyllabus().isEmpty(1,0)) 
        self.assertTrue(lecturer.getSyllabus().isEmpty(2,1)) 

if __name__ == '__main__':
    unittest.main()