import unittest
from Advisor import Advisor
from Student import Student
from Id import Id
from Password import Password
from Course import Course
from CourseType import CourseType
from CourseSchedule import CourseSchedule
from Hour import Hour
from Day import Day
from Mark import Mark

class AdvisorTest(unittest.TestCase):

    def test_awaiting_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.set_request("true")
        student2 = Student("Kerem", "Aydin",Id("150120070"), Password("Kerem_123"),advisor,None,None)
        student3 = Student("Yilmaz","Özdamar",Id("150120050"), Password("ElliYasinda12"), advisor,None,None)
        student3.set_request("true")
        advisor.setStudents([student,student2,student3])
        advisor.findAwaitingStudents()
        self.assertEqual(2,len(advisor.getAwaitingStudents()))
        self.assertEqual("Yilmaz", advisor.getAwaitingStudents()[1].get_first_name())
        advisor.select_student(1)
        self.assertEqual("Eren",advisor.getSelectStudent().get_first_name())

    
    def test_login(self):
        
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        advisor.setGivenCourses([course4,course3])

        newId = "a1501002"
        persons = [advisor]
        data = [newId,advisor.get_password().get_password()]
        username = data[0]
        password = data[1]
        self.assertTrue(isinstance(advisor,Advisor))
        self.assertEqual(True,"a" + str("1501002") == username)
        self.assertEqual(True,password == "sql112233")
        return_advisor = advisor.login([newId,advisor.get_password().get_password()],[advisor])
        self.assertTrue(isinstance(return_advisor,Advisor))
        self.assertEqual(0, len(return_advisor.getAwaitingStudents()))
        self.assertFalse(advisor.get_syllabus().is_empty(0,1))
        self.assertFalse(advisor.get_syllabus().is_empty(1,1))
        self.assertFalse(advisor.get_syllabus().is_empty(0,0))
        self.assertFalse(advisor.get_syllabus().is_empty(1,0)) 
        self.assertTrue(advisor.get_syllabus().is_empty(2,1)) 
        
    def test_syllabus(self):
        
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        advisor.setGivenCourses([course4,course3])

        advisor.create_syllabus(advisor.getGivenCourses())
        self.assertFalse(advisor.get_syllabus().is_empty(0,1))
        self.assertFalse(advisor.get_syllabus().is_empty(1,1))
        self.assertFalse(advisor.get_syllabus().is_empty(0,0))
        self.assertFalse(advisor.get_syllabus().is_empty(1,0)) 
        self.assertTrue(advisor.get_syllabus().is_empty(2,1))       

    def test_approve(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.set_request("true")
        advisor.setStudents([student])
        advisor.findAwaitingStudents()
        advisor.select_student(1)

        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   

        student.set_selected_courses([course4,course3])
        advisor.approve()
        self.assertEqual(2,len(student.get_approved_courses()))
        self.assertEqual("Introduction to Modern Biology",student.get_approved_courses()[0].get_course_name())
        self.assertEqual("Eren",course4.get_student_list()[0].get_first_name())
        self.assertEqual(0,len(student.get_selected_courses()))
        self.assertEqual("null",student.get_request())
        self.assertEqual(0,len(advisor.getAwaitingStudents()))
        self.assertEqual(None, advisor.getSelectStudent())

    def test_disapprove(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.set_request("true")
        advisor.setStudents([student])
        advisor.findAwaitingStudents()
        advisor.select_student(1)

        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   

        student.set_selected_courses([course4,course3])
        advisor.disapprove()
        self.assertEqual(0,len(student.get_selected_courses()))
        self.assertEqual("false",student.get_request())
        self.assertEqual(0,len(advisor.getAwaitingStudents()))  
        self.assertEqual(None, advisor.getSelectStudent())      

    def test_send_approved_message_to_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.unread_notifications = []
        student.read_notifications = []
        advisor.setSelectStudent(student)
        message = ""
        message_type = "A"
        advisor.sendNotification(message, message_type)
        self.assertEqual(1, len(student.unread_notifications))
        self.assertEqual("The request is approved!", student.unread_notifications[0])

    def test_send_disapproved_message_to_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.unread_notifications = []
        student.read_notifications = []
        advisor.setSelectStudent(student)
        message = ""
        message_type = "D"
        advisor.sendNotification(message, message_type)
        self.assertEqual(1, len(student.unread_notifications))
        self.assertEqual("The request is disapproved!", student.unread_notifications[0])

    def test_send_message_to_the_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.unread_notifications = []
        student.read_notifications = []
        advisor.setSelectStudent(student)
        message = "You have a busy program but I still approved"
        advisor.sendNotification(message, "A")
        self.assertEqual(1, len(student.unread_notifications))
        self.assertEqual("You have a busy program but I still approved",student.unread_notifications[0])

if __name__ == '__main__':
    unittest.main()
