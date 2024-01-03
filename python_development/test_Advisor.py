import unittest
from Advisor import Advisor
from Student import Student
from Id import Id
from Password import Password

class AdvisorTest(unittest.TestCase):


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
