import unittest

from Student import Student
from Course import Course
from Id import Id
from GradeClass import GradeClass
from CourseType import CourseType
from Grade import Grade
from Transcript import Transcript



class TestStudent(unittest.TestCase):

    def testCheckCourseType(self):
        # NONTECHNICAL
        BUS1004 = Course(None, "Strategic Entrepreneurship", 0, 3, None, None, 0, CourseType.NONTECHNICAL)

        # CASE1: Student term < 2
        term1 = Transcript(0, 1, [], None)
        studentTerm1 = Student(None, None, None, None, None, term1, None)
        self.assertFalse(studentTerm1.checkCourseType(BUS1004))

        # CASE2: Student term >= 2
        term2 = Transcript(0, 2, [], None)
        studentTerm2 = Student(None, None, None, None, None, term2, None)
        self.assertTrue(studentTerm2.checkCourseType(BUS1004))

        # TECHNICAL
        CSE4217 = Course(None, "Microprocessors", 0, 7, None, None, 0, CourseType.TECHNICAL)

        # CASE 1: Student term = 7
        term7 = Transcript(0, 7, [], None)
        studentTerm7 = Student(None, None, None, None, None, term7, None)
        self.assertTrue(studentTerm7.checkCourseType(CSE4217))

        # CASE 2: Student term != 7 OR 8
        self.assertFalse(studentTerm1.checkCourseType(CSE4217))

        # FACULTY
        EE4062 = Course(None, "Introduction to Image Processing", 0, 0, None, None, 0, CourseType.FACULTY)

        # CASE 1: Student term == 7
        self.assertTrue(studentTerm7.checkCourseType(EE4062))

        # CASE 2: Student term != 7
        self.assertFalse(studentTerm1.checkCourseType(EE4062))

        # MANDATORY
        ATA121 = Course(None, "Ataturk Ilkeleri ve Inkilap Tarihi I", 0, 1, None, None, 0, CourseType.MANDATORY)
        Engineering_Project_I = Course(None, "Engineering Project I", 0, 0, None, None, 0, CourseType.MANDATORY)
        Engineering_Project_II = Course(None, "Engineering Project II", 0, 0, None, None, 0, CourseType.MANDATORY)

        # Engineering Project I and Engineering Project II

        # CASE1: COURSE: Engineering Project I, Student's credit >= 165
        term7.setTotalCredit(180)
        self.assertTrue(studentTerm7.checkCourseType(Engineering_Project_I))

        # CASE2: COURSE: Engineering Project I, Student's credit < 165
        term7.setTotalCredit(100)
        self.assertFalse(studentTerm7.checkCourseType(Engineering_Project_I))

        # CASE3: COURSE: Engineering Project II, Student's credit >= 165 TRUE
        term7.setTotalCredit(180)
        self.assertTrue(studentTerm7.checkCourseType(Engineering_Project_II))

        # CASE4: COURSE: Engineering Project II, Student's credit < 165 FALSE
        term7.setTotalCredit(90)
        self.assertFalse(studentTerm7.checkCourseType(Engineering_Project_II))

        # Taking the course of his own term
        # CASE5: course's term == Student's term
        self.assertFalse(studentTerm2.checkCourseType(ATA121))

        # Is Sagligi ve Guvenligi I and Is Sagligi ve Guvenligi II
        isgI = Course(None, "Is Sagligi ve Guvenligi I", 0, 7, None, None, 0, CourseType.MANDATORY)
        term3 = Transcript(2.5, 3, [], None)
        studentTerm3 = Student(None, None, None, None, None, term3, None)

        # CASE6: Student's gpa < 3.0 && Student's year >= 3
        self.assertFalse(studentTerm3.checkCourseType(isgI))

        # Taking a course at a higher level"
        # CAS7: Student's gpa >= 3.0 && Student's year < 3
        term3_1 = Transcript(3.1, 1, [], None)
        studentTerm3_1 = Student(None, None, None, None, None, term3_1, None)
        self.assertFalse(studentTerm3_1.checkCourseType(isgI))

        # CASE8: Student's gpa >= 3.0 && Student's year >= 3
        term3_2 = Transcript(3.1, 3, [], None)
        studentTerm3_2 = Student(None, None, None, None, None, term3_2, None)
        self.assertTrue(studentTerm3_2.checkCourseType(isgI))

        # CASE9: Student's gpa >= 3.0 && Student's year >= 3 course's year == Student's year +2
        courseTerm5 = Course(None, "Digital Logic Design", 0, 5, None, None, 0, CourseType.MANDATORY)
        self.assertTrue(studentTerm3_2.checkCourseType(courseTerm5))

        # CASE10: Student's gpa >= 3.0 && Student's year >= 3 course's != Student's year +2
        courseTerm7 = Course(None, "Computer Networks", 0, 7, None, None, 0, CourseType.MANDATORY)
        self.assertFalse(studentTerm3_2.checkCourseType(courseTerm7))

    def testIsFailedCourse(self):
        id1 = Id("CSE2023")
        id2 = Id("MATH1002")

        # Creating sample courses
        course1 = Course(id1, None, 0, 3, None, None, 0, None)
        course2 = Course(id2, None, 0, 2, None, None, 0, None)

        # Creating sample GradeClass objects
        passedCourse1 = GradeClass(course1, Grade.AA)
        failedCourse1 = GradeClass(course2, Grade.FF)

        # passedCourses and failedCourses ArrayLists are created and objects are added to these lists
        passedCourses = [passedCourse1]
        failedCourses = [failedCourse1]

        transcript = Transcript(0, 4, passedCourses, failedCourses)

        # Creating a Student object and adding a Transcript object to this object
        student = Student(None, None, None, None, None, transcript, None)

        # Must return false because the first lesson was successful
        self.assertFalse(student.isFailedCourse(course1))

        # Must return true because second lesson failed
        self.assertTrue(student.isFailedCourse(course2))

    def testExceed(self):
        # Creating an ArrayList to store passed courses
        passedCourses = []

        # Creating a Transcript with initial values and an empty list of passed courses
        transcript = Transcript(0, 0, passedCourses, None)

        # Creating a Student with null values for personal information, using the created transcript
        student = Student(None, None, None, None, None, transcript, None)

        # Creating the first course and adding it to the list of passed courses with grade AA
        course1 = Course(None, None, 0, 0, None, None, 0, CourseType.NONTECHNICAL)
        gradeClass1 = GradeClass(course1, Grade.AA)
        passedCourses.append(gradeClass1)

        # Asserting that the student did not exceed the limit for NONTECHNICAL courses with max limit 2
        self.assertFalse(student.exceed(course1.getCourseType(), 2))

        # Creating the second course and adding it to the list of passed courses with grade AA
        course2 = Course(None, None, 0, 0, None, None, 0, CourseType.NONTECHNICAL)
        gradeClass2 = GradeClass(course1, Grade.AA)
        passedCourses.append(gradeClass2)

        # Asserting that the student exceeded the limit for NONTECHNICAL courses with max limit 2
        self.assertTrue(student.exceed(course2.getCourseType(), 2))

        # Creating the third course and adding it to the list of passed courses with grade AA
        course3 = Course(None, None, 0, 0, None, None, 0, CourseType.FACULTY)
        gradeClass3 = GradeClass(course3, Grade.AA)
        passedCourses.append(gradeClass3)

        # Asserting that the student did not exceed the limit for FACULTY courses with max limit 2
        self.assertFalse(student.exceed(course3.getCourseType(), 2))

        # Creating the fourth course and adding it to the list of passed courses with grade AA
        course4 = Course(None, None, 0, 0, None, None, 0, CourseType.FACULTY)
        gradeClass4 = GradeClass(course4, Grade.AA)
        passedCourses.append(gradeClass4)

        # Asserting that the student exceeded the limit for FACULTY courses with max limit 2
        self.assertTrue(student.exceed(course4.getCourseType(), 2))

    def testExceedTerm(self):
        student = Student(None, None, None, None, None, None, None)

        # CASE 1: Check status if student does not select specific course type in the current term
        self.assertFalse(student.exceedTerm(CourseType.MANDATORY))
        self.assertFalse(student.exceedTerm(CourseType.TECHNICAL))
        self.assertFalse(student.exceedTerm(CourseType.FACULTY))

        # CASE2: If student selects one specific course type in the current term
        nonTechnical = Course(None, "Strategic Entrepreneurship", 0, 3, None, None, 0, CourseType.NONTECHNICAL)
        technical = Course(None, "Microprocessors", 0, 3, None, None, 0, CourseType.TECHNICAL)
        faculty = Course(None, "Introduction to Image Processing", 0, 3, None, None, 0, CourseType.FACULTY)

        selectedCourses = [nonTechnical, technical, faculty]
        student.setSelectedCourses(selectedCourses)

        self.assertTrue(student.exceedTerm(CourseType.NONTECHNICAL))
        self.assertTrue(student.exceedTerm(CourseType.TECHNICAL))
        self.assertTrue(student.exceedTerm(CourseType.FACULTY))

    def testClearUnreadNotification(self):
        student = Student(None, None, None, None, None, None, None)

        unreadNotifications = []
        readNotifications = []
        student.setUnreadNotifications(unreadNotifications)
        student.setReadNotifications(readNotifications)

        student.addUnreadNotification("Notification 1")
        student.addUnreadNotification("Notification 2")

        student.clearUnreadNotification()

        self.assertTrue(len(student.getUnreadNotifications()) == 0)
        self.assertFalse(len(student.getReadNotifications()) == 0)

    def testAddUnreadNotification(self):
        student = Student(None, None, None, None, None, None, None)

        student.setUnreadNotifications([])

        notification = "Test Notification"
        student.addUnreadNotification(notification)

        self.assertTrue(notification in student.getUnreadNotifications())
        self.assertEqual(notification, student.getUnreadNotifications()[0])
