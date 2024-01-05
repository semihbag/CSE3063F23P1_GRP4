import unittest
from python_development.PersonObject.Advisor import Advisor
from python_development.PersonObject.Student import Student
from python_development.PersonObject.Id import Id
from python_development.PersonObject.Password import Password
from python_development.CourseObject.Course import Course
from python_development.CourseObject.CourseType import CourseType
from python_development.CourseObject.CourseSchedule import CourseSchedule
from python_development.CourseObject.Hour import Hour
from python_development.CourseObject.Day import Day


class AdvisorTest(unittest.TestCase):

    def test_awaiting_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.setRequest("true")
        student2 = Student("Kerem", "Aydin",Id("150120070"), Password("Kerem_123"),advisor,None,None)
        student3 = Student("Yilmaz","Özdamar",Id("150120050"), Password("ElliYasinda12"), advisor,None,None)
        student3.setRequest("true")
        advisor.setStudents([student,student2,student3])
        advisor.findAwaitingStudents()
        self.assertEqual(2,len(advisor.getAwaitingStudents()))
        self.assertEqual("Yilmaz", advisor.getAwaitingStudents()[1].getFirstName())
        advisor.selectStudent(1)
        self.assertEqual("Eren",advisor.getSelectStudent().getFirstName())

    
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
        data = [newId,advisor.getPassword().getPassword()]
        username = data[0]
        password = data[1]
        self.assertTrue(isinstance(advisor,Advisor))
        self.assertEqual(True,"a" + str("1501002") == username)
        self.assertEqual(True,password == "sql112233")
        return_advisor = advisor.login([newId,advisor.getPassword().getPassword()],[advisor])
        self.assertTrue(isinstance(return_advisor,Advisor))
        self.assertEqual(0, len(return_advisor.getAwaitingStudents()))
        self.assertFalse(advisor.getSyllabus().isEmpty(0,1))
        self.assertFalse(advisor.getSyllabus().isEmpty(1,1))
        self.assertFalse(advisor.getSyllabus().isEmpty(0,0))
        self.assertFalse(advisor.getSyllabus().isEmpty(1,0)) 
        self.assertTrue(advisor.getSyllabus().isEmpty(2,1)) 
        
    def test_syllabus(self):
        
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   
        advisor.setGivenCourses([course4,course3])

        advisor.createSyllabus(advisor.getGivenCourses())
        self.assertFalse(advisor.getSyllabus().isEmpty(0,1))
        self.assertFalse(advisor.getSyllabus().isEmpty(1,1))
        self.assertFalse(advisor.getSyllabus().isEmpty(0,0))
        self.assertFalse(advisor.getSyllabus().isEmpty(1,0)) 
        self.assertTrue(advisor.getSyllabus().isEmpty(2,1))     

    def test_approve(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.setRequest("true")
        advisor.setStudents([student])
        advisor.findAwaitingStudents()
        advisor.selectStudent(1)

        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   

        student.setSelectedCourses([course4,course3])
        advisor.approve()
        self.assertEqual(2,len(student.getApprovedCourses()))
        self.assertEqual("Introduction to Modern Biology",student.getApprovedCourses()[0].getCourseName())
        self.assertEqual("Eren",course4.getStudentList()[0].getFirstName())
        self.assertEqual(0,len(student.getSelectedCourses()))
        self.assertEqual("null",student.getRequest())
        self.assertEqual(0,len(advisor.getAwaitingStudents()))
        self.assertEqual(None, advisor.getSelectStudent())

    def test_disapprove(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.setRequest("true")
        advisor.setStudents([student])
        advisor.findAwaitingStudents()
        advisor.selectStudent(1)

        course_hour4 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules4 = [CourseSchedule(Day.MONDAY, course_hour4)]
        course4 = Course(Id("MBG1201"), "Introduction to Modern Biology", 15, 1, None, course_schedules4, 5, CourseType.MANDATORY)

        course_hour3 = [Hour.H_08_30_09_20, Hour.H_09_30_10_20]
        course_schedules3 = [CourseSchedule(Day.TUESDAY, course_hour3)]
        course3 = Course(Id("CSE1200"), "Introduction to Computer Engineering", 15, 1, None, course_schedules3, 4, CourseType.MANDATORY)   

        student.setSelectedCourses([course4,course3])
        advisor.disapprove()
        self.assertEqual(0,len(student.getSelectedCourses()))
        self.assertEqual("false",student.getRequest())
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
        self.assertEqual(1, len(student.getUnreadNotifications()))
        self.assertEqual("The request is approved!", student.getUnreadNotifications()[0])

    def test_send_disapproved_message_to_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.unread_notifications = []
        student.read_notifications = []
        advisor.setSelectStudent(student)
        message = ""
        message_type = "D"
        advisor.sendNotification(message, message_type)
        self.assertEqual(1, len(student.getUnreadNotifications()))
        self.assertEqual("The request is disapproved!", student.getUnreadNotifications()[0])

    def test_send_message_to_the_student(self):
        advisor = Advisor("Mustafa", "Agaoglu", Id("1501002"), Password("sql112233"))
        student = Student("Eren", "Cetin", Id("150120008"), Password("Eren_123"), advisor, None, None)
        student.unread_notifications = []
        student.read_notifications = []
        advisor.setSelectStudent(student)
        message = "You have a busy program but I still approved"
        advisor.sendNotification(message, "A")
        self.assertEqual(1, len(student.getUnreadNotifications()))
        self.assertEqual("You have a busy program but I still approved",student.getUnreadNotifications()[0])

if __name__ == '__main__':
    unittest.main()
